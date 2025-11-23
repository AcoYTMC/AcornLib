package net.acoyt.acornlib.mixin.sculk;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.acoyt.acornlib.impl.util.AcornLibUtils.acoUuid;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;

@Mixin(VibrationSystem.Listener.class)
public class VibrationListenerMixin {
    @Inject(
            method = "listen(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/world/event/GameEvent$Emitter;Lnet/minecraft/util/math/Vec3d;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornLib$listen(ServerLevel world, Holder<GameEvent> event, GameEvent.Context emitter, Vec3 emitterPos, CallbackInfoReturnable<Boolean> cir) {
        Entity var7 = emitter.sourceEntity();
        if (var7 instanceof Player player) {
            if (player.getUUID().equals(acoUuid)) {
                cir.setReturnValue(false);
            }
        }
    }
}
