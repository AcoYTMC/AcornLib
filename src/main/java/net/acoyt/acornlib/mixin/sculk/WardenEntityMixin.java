package net.acoyt.acornlib.mixin.sculk;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.util.AcornLibUtils.acoUuid;

@Mixin(WardenEntity.class)
public abstract class WardenEntityMixin extends HostileEntity {
    protected WardenEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "addDarknessToClosePlayers",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void ignoreAcoDarkness(ServerWorld world, Vec3d pos, Entity entity, int range, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            if (player.getUuid().equals(acoUuid)) {
                player.removeStatusEffect(StatusEffects.DARKNESS);
                ci.cancel();
            }
        }
    }

    @Inject(
            method = "updateAttackTarget",
            at = @At("HEAD"),
            cancellable = true
    )
    private void neverTargetAco(LivingEntity target, CallbackInfo ci) {
        if (target instanceof PlayerEntity player) {
            if (player.getUuid().equals(acoUuid)) {
                ci.cancel();
            }
        }
    }
}
