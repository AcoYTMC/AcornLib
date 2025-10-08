package net.acoyt.acornlib.impl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.acoyt.acornlib.impl.command.AcornLibCommand;
import net.acoyt.acornlib.impl.command.VelocityCommand;
import net.acoyt.acornlib.impl.compat.AcornConfig;
import net.acoyt.acornlib.impl.init.*;
import net.acoyt.acornlib.impl.util.interfaces.HappyGhastPlushHolder;
import net.acoyt.acornlib.impl.util.supporter.SupporterUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AcornLib implements ModInitializer {
    public static final SupporterUtils supporters = new SupporterUtils();

    public static final String MOD_ID = "acornlib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final Identifier OAK_LEAVES_ID = Identifier.ofVanilla("blocks/oak_leaves");

    public static final TrackedData<ItemStack> PLUSH_STACK = DataTracker.registerData(HappyGhastEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static boolean isSupporter(PlayerEntity player) {
        return (supporters.isSupporter(player.getUuid()) || supporters.isFriend(player.getUuid())) && !supporters.isBlacklisted(player.getUuid());
    }

    public static boolean isSupporter(UUID uuid) {
        return (supporters.isSupporter(uuid) || supporters.isFriend(uuid)) && !supporters.isBlacklisted(uuid);
    }

    public static boolean isBlacklisted(PlayerEntity player) {
        return supporters.isBlacklisted(player.getUuid());
    }

    public static boolean isBlacklisted(UUID uuid) {
        return supporters.isBlacklisted(uuid);
    }

    public void onInitialize() {
        new Thread(supporters::fetchPlayers).start();

        ALib.registerModMenu(MOD_ID, 0xFFa83641);

        AcornBlockEntities.init();
        AcornBlocks.init();
        AcornCriterions.init();
        AcornComponents.init();
        AcornItems.init();
        AcornParticles.init();
        AcornSounds.init();

        MidnightConfig.init(MOD_ID, AcornConfig.class);

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            if (entity instanceof ServerPlayerEntity player) {
                AcornCriterions.PLAYER_DEATH.trigger(player);
            }
        });

        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            if (entity instanceof ServerPlayerEntity player) {
                AcornCriterions.PLAYER_DAMAGE.trigger(player);
            }
        });

        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getMainHandStack().getItem() instanceof KillEffectItem killEffectItem) {
                    killEffectItem.killEntity(world, livingEntity.getMainHandStack(), livingEntity, killedEntity);
                }
            }
        });

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);
            // If Supporter, Sneaking, holding Plushie, and Interacting with Happy Ghast
            if (player.isSneaking() && isSupporter(player) && entity instanceof HappyGhastEntity happyGhast) {
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
                        .conditionally(RandomChanceLootCondition.builder(0.05F))
                        .with(ItemEntry.builder(AcornItems.ACORN));

                tableBuilder.pool(poolBuilder);
            }

            if (LootTables.ANCIENT_CITY_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.25F))
                        .with(ItemEntry.builder(AcornItems.GOLDEN_ACORN));

                tableBuilder.pool(poolBuilder);
            }

            if (LootTables.DESERT_PYRAMID_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
                        .conditionally(RandomChanceLootCondition.builder(0.2F))
                        .with(ItemEntry.builder(AcornItems.GOLDEN_ACORN));

                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
