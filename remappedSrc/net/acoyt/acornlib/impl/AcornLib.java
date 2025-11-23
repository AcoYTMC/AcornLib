package net.acoyt.acornlib.impl;

import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.acoyt.acornlib.impl.command.AcornLibCommand;
import net.acoyt.acornlib.impl.command.VelocityCommand;
import net.acoyt.acornlib.compat.AcornConfig;
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
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AcornLib implements ModInitializer {
    public static final SupporterUtils supporters = new SupporterUtils();

    public static final String MOD_ID = "acornlib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final ResourceLocation OAK_LEAVES_ID = ResourceLocation.withDefaultNamespace("blocks/oak_leaves");

    public static final EntityDataAccessor<ItemStack> PLUSH_STACK = SynchedEntityData.defineId(HappyGhast.class, EntityDataSerializers.ITEM_STACK);

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static boolean isSupporter(Player player) {
        return (supporters.isSupporter(player.getUUID()) || supporters.isFriend(player.getUUID())) && !supporters.isBlacklisted(player.getUUID());
    }

    public static boolean isSupporter(UUID uuid) {
        return (supporters.isSupporter(uuid) || supporters.isFriend(uuid)) && !supporters.isBlacklisted(uuid);
    }

    public static boolean isBlacklisted(Player player) {
        return supporters.isBlacklisted(player.getUUID());
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
            if (entity instanceof ServerPlayer player) {
                AcornCriterions.PLAYER_DEATH.trigger(player);
            }
        });

        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            if (entity instanceof ServerPlayer player) {
                AcornCriterions.PLAYER_DAMAGE.trigger(player);
            }
        });

        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getMainHandItem().getItem() instanceof KillEffectItem killEffectItem) {
                    killEffectItem.killEntity(world, livingEntity.getMainHandItem(), livingEntity, killedEntity);
                }
            }
        });

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
            // If Supporter, Sneaking, holding Plushie, and Interacting with Happy Ghast
            if (player.isShiftKeyDown() && isSupporter(player) && entity instanceof HappyGhast happyGhast) {
                if (happyGhast instanceof HappyGhastPlushHolder holder) {
                    if (!happyGhast.hasExactlyOnePlayerPassenger()) {
                        if (stack.is(AcornTags.PLUSHIES) && !holder.acornLib$getPlushStack().equals(stack)) {
                            holder.acornLib$setPlushStack(stack); // Set internally held stack
                            player.playSound(SoundEvents.HARNESS_EQUIP.value(), 1.0F, 1.0F);
                        }

                        if (stack.isEmpty() && !holder.acornLib$getPlushStack().isEmpty()) {
                            holder.acornLib$setPlushStack(ItemStack.EMPTY);
                            player.playSound(SoundEvents.HARNESS_UNEQUIP, 1.0F, 1.0F);
                        }
                    }
                }
            }
            return InteractionResult.PASS;
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, acc, dedicated) -> {
            VelocityCommand.register(dispatcher);

            if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                AcornLibCommand.register(dispatcher);
            }
        });

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if (OAK_LEAVES_ID.equals(key.location())) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1.0F, 1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.05F))
                        .add(LootItem.lootTableItem(AcornItems.ACORN));

                tableBuilder.withPool(poolBuilder);
            }

            if (BuiltInLootTables.ANCIENT_CITY.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.25F))
                        .add(LootItem.lootTableItem(AcornItems.GOLDEN_ACORN));

                tableBuilder.withPool(poolBuilder);
            }

            if (BuiltInLootTables.DESERT_PYRAMID.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1.0F, 2.0F))
                        .when(LootItemRandomChanceCondition.randomChance(0.2F))
                        .add(LootItem.lootTableItem(AcornItems.GOLDEN_ACORN));

                tableBuilder.withPool(poolBuilder);
            }
        });
    }
}
