package net.acoyt.acornlib.mixin.advantages;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.Vibrations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.acoyt.acornlib.impl.util.Util.hasAdvantages;

@Mixin(Vibrations.VibrationListener.class)
public class VibrationListenerMixin {
    @Inject(
            method = "listen(Lnet/minecraft/server/world/ServerWorld;" +
                    "Lnet/minecraft/registry/entry/RegistryEntry;" +
                    "Lnet/minecraft/world/event/GameEvent$Emitter;" +
                    "Lnet/minecraft/util/math/Vec3d;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornlib$listen(ServerWorld world, RegistryEntry<GameEvent> event, GameEvent.Emitter emitter, Vec3d emitterPos, CallbackInfoReturnable<Boolean> cir) {
        if (hasAdvantages(emitter.sourceEntity())) {
            cir.setReturnValue(false);
        }
    }
}
