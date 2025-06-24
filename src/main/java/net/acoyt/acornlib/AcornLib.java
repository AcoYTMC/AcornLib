package net.acoyt.acornlib;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.command.AcornLibCommand;
import net.acoyt.acornlib.command.VelocityCommand;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.init.*;
import net.acoyt.acornlib.item.KillEffectItem;
import net.acoyt.acornlib.item.TestItem;
import net.acoyt.acornlib.util.interfaces.HappyGhastPlushHolder;
import net.acoyt.acornlib.util.supporter.SupporterUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class AcornLib implements ModInitializer {
	public static final String MOD_ID = "acornlib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static String currentModId = "";

	private static final Identifier OAK_LEAVES_ID = Identifier.ofVanilla("blocks/oak_leaves");

    public static final TrackedData<ItemStack> PLUSH_STACK = DataTracker.registerData(HappyGhastEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	public static Identifier cid(String path) {
		return Identifier.of(currentModId, path);
	}

	public void onInitialize() {
		new Thread(SupporterUtils::fetchPlayers).start();

		AcornBlocks.init();
        AcornCriterions.init();
		AcornComponents.init();
		AcornItems.init();
		AcornParticles.init();
		AcornSounds.init();

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
            // If Supporter, Sneaking, holding Plushie, and Interacting with Happy Ghast
            if (player.isSneaking() && SupporterUtils.isSupporterInGeneral(player) && entity instanceof HappyGhastEntity happyGhast) {
                if (happyGhast instanceof HappyGhastPlushHolder holder) {
                    if (!happyGhast.hasPlayerRider()) {
                        if (stack.isIn(AcornTags.PLUSHIES) && !holder.acornLib$getPlushStack().equals(stack)) {
                            holder.acornLib$setPlushStack(stack); // Set internally held stack
                            player.playSound(SoundEvents.ENTITY_HAPPY_GHAST_EQUIP.value(), 1.0F, 1.0F);
                        }

                        if (stack.isEmpty() && !holder.acornLib$getPlushStack().isEmpty()) {
                            holder.acornLib$setPlushStack(ItemStack.EMPTY);
                            player.playSound(SoundEvents.ENTITY_HAPPY_GHAST_UNEQUIP, 1.0F, 1.0F);
                        }
                    }
                }
            }
            return ActionResult.PASS;
        });

		MidnightConfig.init(MOD_ID, AcornConfig.class);

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			Item TEST_ITEM = new TestItem(new Item.Settings()
					.component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
					.registryKey(RegistryKey.of(RegistryKeys.ITEM, id("test_item"))));

			Registry.register(Registries.ITEM, id("test_item"), TEST_ITEM);
		}

		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, victim) -> {
			if (entity instanceof LivingEntity living) {
				ItemStack stack = living.getMainHandStack();
				if (stack.getItem() instanceof KillEffectItem killEffectItem) {
					killEffectItem.killEntity(world, stack, living, victim);
				}
			}
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, acc, dedicated) -> {
			VelocityCommand.register(dispatcher);

			if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
				AcornLibCommand.register(dispatcher);
			}
		});

		LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
			if (OAK_LEAVES_ID.equals(key.getValue())) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1.0F, 1.0F))
						.conditionally(RandomChanceLootCondition.builder(0.075F))
						.with(ItemEntry.builder(AcornItems.ACORN).build());

				tableBuilder.pool(poolBuilder.build());
			}

			if (LootTables.DESERT_PYRAMID_CHEST.equals(key)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
						.conditionally(RandomChanceLootCondition.builder(0.1F))
						.with(ItemEntry.builder(AcornItems.GOLDEN_ACORN).build());

				tableBuilder.pool(poolBuilder.build());
			}
		});
	}
}
