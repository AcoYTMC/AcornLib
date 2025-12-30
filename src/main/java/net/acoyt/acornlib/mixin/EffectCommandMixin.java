package net.acoyt.acornlib.mixin;

import net.acoyt.acornlib.api.effect.UnclearableEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.EffectCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(EffectCommand.class)
public class EffectCommandMixin {
    @Inject(method = "executeClear(Lnet/minecraft/server/command/ServerCommandSource;Ljava/util/Collection;)I", at = @At("TAIL"))
    private static void resetEffectInstance(ServerCommandSource source, Collection<? extends Entity> targets, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity living) {
                for (StatusEffectInstance instance : living.getActiveStatusEffects().values()) {
                    if (instance.getEffectType().value() instanceof UnclearableEffect) {
                        living.removeStatusEffect(instance.getEffectType());
                    }
                }
            }
        }
    }

    @Inject(method = "executeClear(Lnet/minecraft/server/command/ServerCommandSource;Ljava/util/Collection;Lnet/minecraft/registry/entry/RegistryEntry;)I", at = @At("HEAD"))
    private static void resetEffectInstance(ServerCommandSource source, Collection<? extends Entity> targets, RegistryEntry<StatusEffect> statusEffect, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity living) {
                for (StatusEffectInstance instance : living.getActiveStatusEffects().values()) {
                    if (instance.getEffectType().value() instanceof UnclearableEffect) {
                        living.removeStatusEffect(instance.getEffectType());
                    }
                }
            }
        }
    }
}
