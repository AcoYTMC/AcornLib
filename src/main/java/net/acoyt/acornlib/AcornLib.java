package net.acoyt.acornlib;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.command.AcornLibCommand;
import net.acoyt.acornlib.command.VelocityCommand;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.init.*;
import net.acoyt.acornlib.item.KillEffectItem;
import net.acoyt.acornlib.item.TestItem;
import net.acoyt.acornlib.networking.AttackingPayload;
import net.acoyt.acornlib.networking.UsingPayload;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
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
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class AcornLib implements ModInitializer {
	public static final String MOD_ID = "acornlib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static String currentModId = "";

	private static final Identifier OAK_LEAVES_ID = Identifier.ofVanilla("blocks/oak_leaves");

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	public static Identifier cid(String path) {
		return Identifier.of(currentModId, path);
	}

	public void onInitialize() {
		new Thread(AcornLibUtils.supporterUtils::fetchPlayers).start();

		AcornBlocks.init();
		AcornComponents.init();
        AcornEntities.init();
		AcornItems.init();
		AcornParticles.init();
		AcornSounds.init();

		registerPackets();

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

	public void registerPackets() {
		PayloadTypeRegistry.playC2S().register(AttackingPayload.ID, AttackingPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(UsingPayload.ID, UsingPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(AttackingPayload.ID, new AttackingPayload.Receiver());
		ServerPlayNetworking.registerGlobalReceiver(UsingPayload.ID, new UsingPayload.Receiver());
	}
}