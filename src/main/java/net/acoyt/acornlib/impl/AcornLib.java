package net.acoyt.acornlib.impl;

import com.mojang.logging.LogUtils;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.command.AcornLibCommand;
import net.acoyt.acornlib.impl.command.HudDataCommand;
import net.acoyt.acornlib.impl.command.PerspectiveCommand;
import net.acoyt.acornlib.impl.command.VelocityCommand;
import net.acoyt.acornlib.impl.event.KilledOtherEntityEvent;
import net.acoyt.acornlib.impl.event.PlayerDamageCriterionEvent;
import net.acoyt.acornlib.impl.event.PlayerDeathCriterionEvent;
import net.acoyt.acornlib.impl.event.general.PerspectiveEvents;
import net.acoyt.acornlib.impl.index.*;
import net.acoyt.acornlib.impl.networking.AcornNetworking;
import net.acoyt.acornlib.impl.util.LootTableModifiers;
import net.acoyt.acornlib.impl.util.supporter.SupporterUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;

import java.util.UUID;

import static net.acoyt.acornlib.api.util.MiscUtils.ifDev;

//? if > 1.21.5 {
import net.acoyt.acornlib.impl.event.EquipHappyGhastPlushEvent;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
//? }

//? if > 1.21.1 {
import net.acoyt.acornlib.api.helper.ArmorAttributesHelper;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
//? }

/**
 * @author AcoYT
 */
public class AcornLib implements ModInitializer {
    public static final boolean isMidnightLibLoaded = FabricLoader.getInstance().isModLoaded("midnightlib");
    public static final SupporterUtils supporters = new SupporterUtils();

    public static final String MOD_ID = /*$ mod_id*/ "acornlib";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String VERSION = /*$ mod_version*/ "multi-r1";
    public static final String MINECRAFT = /*$ minecraft*/ "1.21.11";

    public static boolean isSupporter(Player player) {
        return isSupporter(player.getUUID());
    }

    public static boolean isSupporter(UUID uuid) {
        return (supporters.isSupporter(uuid) || supporters.isFriend(uuid)) && !isBlacklisted(uuid);
    }

    public static boolean isBlacklisted(Player player) {
        return isBlacklisted(player.getUUID());
    }

    public static boolean isBlacklisted(UUID uuid) {
        return supporters.isBlacklisted(uuid);
    }

    public void onInitialize() {
        new Thread(supporters::fetchPlayers).start();

        ALib.registerModMenu(MOD_ID, 0xFFa83641);
        if (isMidnightLibLoaded) {
            AcornConfig.init(AcornLib.MOD_ID, AcornConfig.class);
        }

        // Initialization
        AcornAttributes.init();
        AcornBlockEntities.init();
        AcornBlocks.init();
        AcornCriteria.init();
        AcornDataComponents.init();
        AcornGameRules.init();
        AcornItems.init();
        AcornParticles.init();
        AcornSounds.init();

        // Networking
        AcornNetworking.registerTypes();
        AcornNetworking.registerServerboundPackets();

        // Events
        //? if > 1.21.1 {
        DefaultItemComponentEvents.MODIFY.register(new ArmorAttributesHelper.Event());
        //? }
        ServerLivingEntityEvents.AFTER_DEATH.register(new PlayerDeathCriterionEvent());
        ServerLivingEntityEvents.AFTER_DAMAGE.register(new PlayerDamageCriterionEvent());

        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new KilledOtherEntityEvent());

        ServerPlayerEvents.JOIN.register(new PerspectiveEvents.ServerJoin());

        //? if > 1.21.5 {
        UseEntityCallback.EVENT.register(new EquipHappyGhastPlushEvent());
        //? }

        // Commands
        CommandRegistrationCallback.EVENT.register(HudDataCommand::register);
        CommandRegistrationCallback.EVENT.register(VelocityCommand::register);
        CommandRegistrationCallback.EVENT.register(PerspectiveCommand::register);
        ifDev(() -> CommandRegistrationCallback.EVENT.register(AcornLibCommand::register));

        // Loot Tables
        LootTableModifiers.init();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
