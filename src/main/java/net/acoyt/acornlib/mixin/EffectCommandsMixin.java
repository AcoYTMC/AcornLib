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

/**
 * @author AcoYT
 */
@Mixin(EffectCommands.class)
public abstract class EffectCommandsMixin {
    @Inject(method = "clearEffects", at = @At("TAIL"))
    private static void acornlib$clearWithCommands(CommandSourceStack commandSourceStack, Collection<? extends Entity> collection, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : collection) {
            if (entity instanceof LivingEntity living) {
                for (MobEffectInstance instance : living.getActiveEffects()) {
                    if (instance.getEffect().value() instanceof UnclearableEffect) {
                        living.removeEffect(instance.getEffect());
                    }
                }
            }
        }
    }

    @Inject(method = "clearEffect", at = @At("TAIL"))
    private static void acornlib$clearWithCommands(CommandSourceStack commandSourceStack, Collection<? extends Entity> collection, Holder<MobEffect> holder, CallbackInfoReturnable<Integer> cir) {
        for (Entity entity : collection) {
            if (entity instanceof LivingEntity living) {
                if (holder.value() instanceof UnclearableEffect) {
                    living.removeEffect(holder);
                }
            }
        }
    }
}
