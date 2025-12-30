package net.acoyt.acornlib.mixin.sculk;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.acoyt.acornlib.impl.util.AcornLibUtils.acoUuid;

@Debug(export = true)
@Mixin(WardenEntity.class)
public abstract class WardenEntityMixin extends HostileEntity {
    @Shadow public abstract Brain<WardenEntity> getBrain();

    @Shadow public abstract @Nullable LivingEntity getTarget();

    protected WardenEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getAngerAtTarget", at = @At("RETURN"))
    private int acornLib$getAngerAtTarget(int original) {
        if (this.getTarget() instanceof PlayerEntity && this.getTarget().getUuid().equals(acoUuid)) {
            return 0;
        }

        return original;
    }

    @Inject(method = "getTarget", at = @At("HEAD"), cancellable = true)
    private void acornLib$getTarget(CallbackInfoReturnable<LivingEntity> cir) {
        if (this.getTargetInBrain() instanceof PlayerEntity && this.getTargetInBrain().getUuid().equals(acoUuid)) {
            cir.setReturnValue(null);
        }
    }

    @ModifyVariable(
            method = "addDarknessToClosePlayers",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectUtil;addEffectToPlayersWithinDistance(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Vec3d;DLnet/minecraft/entity/effect/StatusEffectInstance;I)Ljava/util/List;"),
            argsOnly = true
    )
    private static Entity acornLib$addDarknessToClosePlayers(Entity entity) {
        return entity instanceof PlayerEntity && entity.getUuid().equals(acoUuid) ? null : entity;
    }

    @Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/brain/task/SonicBoomTask;cooldown(Lnet/minecraft/entity/LivingEntity;I)V"), cancellable = true)
    private void acornLib$tryAttack(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (target instanceof PlayerEntity && target.getUuid().equals(acoUuid)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Inject(method = "updateAttackTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/brain/task/SonicBoomTask;cooldown(Lnet/minecraft/entity/LivingEntity;I)V"), cancellable = true)
    private void acornLib$updateAttackTarget(LivingEntity target, CallbackInfo ci) {
        if (target instanceof PlayerEntity && target.getUuid().equals(acoUuid)) {
            ci.cancel();
        }
    }

    @ModifyReturnValue(
            method = "isValidTarget",
            at = @At("RETURN")
    )
    private boolean acornLib$isValidTarget(boolean original, Entity target) {
        return original && !(target instanceof PlayerEntity && target.getUuid().equals(acoUuid));
    }
}
