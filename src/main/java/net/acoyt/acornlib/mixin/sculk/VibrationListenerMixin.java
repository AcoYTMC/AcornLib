package net.acoyt.acornlib.mixin.sculk;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.Vibrations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.acoyt.acornlib.util.AcornLibUtils.acoUuid;

@Mixin(Vibrations.VibrationListener.class)
public class VibrationListenerMixin {
    @Inject(
            method = "listen(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/world/event/GameEvent$Emitter;Lnet/minecraft/util/math/Vec3d;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void sculkIgnoreAco(ServerWorld world, RegistryEntry<GameEvent> event, GameEvent.Emitter emitter, Vec3d emitterPos, CallbackInfoReturnable<Boolean> cir) {
        Entity var7 = emitter.sourceEntity();
        if (var7 instanceof PlayerEntity player) {
            if (player.getUuid().equals(acoUuid)) {
                cir.setReturnValue(false);
            }
        }
    }
}
