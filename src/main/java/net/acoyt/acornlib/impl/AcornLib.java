package net.acoyt.acornlib.impl;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.command.AcornLibCommand;
import net.acoyt.acornlib.impl.command.HudDataCommand;
import net.acoyt.acornlib.impl.command.PerspectiveCommand;
import net.acoyt.acornlib.impl.command.VelocityCommand;
import net.acoyt.acornlib.impl.event.KilledOtherEntityEvent;
import net.acoyt.acornlib.impl.event.PlayerDamageCriterionEvent;
import net.acoyt.acornlib.impl.event.PlayerDeathCriterionEvent;
import net.acoyt.acornlib.impl.event.SendUpdateRulePayloadEvent;
import net.acoyt.acornlib.impl.index.*;
import net.acoyt.acornlib.impl.networking.ForcePerspectivePayload;
import net.acoyt.acornlib.impl.networking.SyncChangingRulePayload;
import net.acoyt.acornlib.impl.util.LootTableModifiers;
import net.acoyt.acornlib.impl.util.supporter.SupporterUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static net.acoyt.acornlib.api.util.MiscUtils.ifDev;

public class AcornLib implements ModInitializer {
    public static final SupporterUtils supporters = new SupporterUtils();

    public static final String MOD_ID = "acornlib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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
        AcornConfig.init(MOD_ID, AcornConfig.class);

        // Initialization
        AcornAttributes.init();
        AcornBlockEntities.init();
        AcornBlocks.init();
        AcornCriterions.init();
        AcornDataComponents.init();
        AcornGameRules.init();
        AcornItems.init();
        AcornParticles.init();
        AcornSounds.init();

        // Events
        ServerLivingEntityEvents.AFTER_DEATH.register(new PlayerDeathCriterionEvent());
        ServerLivingEntityEvents.AFTER_DAMAGE.register(new PlayerDamageCriterionEvent());
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new KilledOtherEntityEvent());
        ServerPlayConnectionEvents.JOIN.register(new SendUpdateRulePayloadEvent());

        // Commands
        CommandRegistrationCallback.EVENT.register(HudDataCommand::register);
        CommandRegistrationCallback.EVENT.register(VelocityCommand::register);
        CommandRegistrationCallback.EVENT.register(PerspectiveCommand::register);
        ifDev(() -> CommandRegistrationCallback.EVENT.register(AcornLibCommand::register));

        // Loot Tables
        LootTableModifiers.init();

        // Networking
        PayloadTypeRegistry.playS2C().register(ForcePerspectivePayload.ID, ForcePerspectivePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SyncChangingRulePayload.ID, SyncChangingRulePayload.CODEC);
    }
}
