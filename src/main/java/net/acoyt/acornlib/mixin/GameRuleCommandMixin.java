package net.acoyt.acornlib.mixin;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.acoyt.acornlib.api.util.NetworkingUtils;
import net.acoyt.acornlib.impl.index.AcornGameRules;
import net.acoyt.acornlib.impl.networking.clientbound.SyncChangingRulePayload;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.level.gamerules.GameRule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author AcoYT
 */
@Mixin(GameRuleCommand.class)
public class GameRuleCommandMixin {
    @Inject(
            method = "setRule",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/gamerules/GameRules;set(Lnet/minecraft/world/level/gamerules/GameRule;Ljava/lang/Object;Lnet/minecraft/server/MinecraftServer;)V"
            )
    )
    private static <T> void acornlib$sendSyncPayloadIfCustom(CommandContext<CommandSourceStack> context, GameRule<T> gameRule, CallbackInfoReturnable<Integer> cir) {
        if (gameRule == AcornGameRules.PERSPECTIVE_CHANGING) {
            boolean value = BoolArgumentType.getBool(context, "value");
            NetworkingUtils.sendForAllPlayers(context.getSource().getLevel(), new SyncChangingRulePayload(value));
        }
    }
}
