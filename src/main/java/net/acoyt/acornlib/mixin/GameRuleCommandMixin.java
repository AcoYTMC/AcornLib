package net.acoyt.acornlib.mixin;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.acoyt.acornlib.api.util.NetworkingUtils;
import net.acoyt.acornlib.impl.index.AcornGameRules;
import net.acoyt.acornlib.impl.networking.SyncChangingRulePayload;
import net.minecraft.server.command.GameRuleCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRuleCommand.class)
public class GameRuleCommandMixin {
    @Inject(
            method = "executeSet",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/GameRules$Rule;set(Lcom/mojang/brigadier/context/CommandContext;Ljava/lang/String;)V"
            )
    )
    private static <T extends GameRules.Rule<T>> void syncIfCustomRule(CommandContext<ServerCommandSource> context, GameRules.Key<T> key, CallbackInfoReturnable<Integer> cir) {
        if (key == AcornGameRules.ALLOW_PERSPECTIVE_CHANGING) {
            boolean value = BoolArgumentType.getBool(context, "value");
            NetworkingUtils.sendForAllPlayers(context.getSource().getWorld(), new SyncChangingRulePayload(value));
        }
    }
}
