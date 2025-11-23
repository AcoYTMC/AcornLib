package net.acoyt.acornlib.mixin;

import net.acoyt.acornlib.api.effect.UnclearableEffect;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.server.commands.EffectCommands;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(EffectCommands.class)
public class EffectCommandMixin {
    @Inject(method = "executeClear(Lnet/minecraft/server/command/ServerCommandSource;Ljava/util/Collection;)I", at = @At("TAIL"))
    private static void resetEffectInstance(CommandSourceStack source, Collection<? extends Entity> targets, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity living) {
                for (MobEffectInstance instance : living.getActiveEffectsMap().values()) {
                    if (instance.getEffect().value() instanceof UnclearableEffect) {
                        living.removeEffect(instance.getEffect());
                    }
                }
            }
        }
    }

    @Inject(method = "executeClear(Lnet/minecraft/server/command/ServerCommandSource;Ljava/util/Collection;Lnet/minecraft/registry/entry/RegistryEntry;)I", at = @At("HEAD"))
    private static void resetEffectInstance(CommandSourceStack source, Collection<? extends Entity> targets, Holder<MobEffect> statusEffect, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity living) {
                for (MobEffectInstance instance : living.getActiveEffectsMap().values()) {
                    if (instance.getEffect().value() instanceof UnclearableEffect) {
                        living.removeEffect(instance.getEffect());
                    }
                }
            }
        }
    }
}
