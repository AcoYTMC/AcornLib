package net.acoyt.acornlib.mixin.sculk;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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
@Mixin(Warden.class)
public abstract class WardenEntityMixin extends Monster {
    @Shadow public abstract Brain<Warden> getBrain();

    @Shadow public abstract @Nullable LivingEntity getTarget();

    protected WardenEntityMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getAngerAtTarget", at = @At("RETURN"))
    private int acornLib$getAngerAtTarget(int original) {
        if (this.getTarget() instanceof Player && this.getTarget().getUUID().equals(acoUuid)) {
            return 0;
        }

        return original;
    }

    @Inject(method = "getTarget", at = @At("HEAD"), cancellable = true)
    private void acornLib$getTarget(CallbackInfoReturnable<LivingEntity> cir) {
        if (this.getTargetFromBrain() instanceof Player && this.getTargetFromBrain().getUUID().equals(acoUuid)) {
            cir.setReturnValue(null);
        }
    }

    @ModifyVariable(
            method = "addDarknessToClosePlayers",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectUtil;addEffectToPlayersWithinDistance(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Vec3d;DLnet/minecraft/entity/effect/StatusEffectInstance;I)Ljava/util/List;"),
            argsOnly = true
    )
    private static Entity acornLib$addDarknessToClosePlayers(Entity entity) {
        return entity instanceof Player && entity.getUUID().equals(acoUuid) ? null : entity;
    }

    @Inject(method = "tryAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/brain/task/SonicBoomTask;cooldown(Lnet/minecraft/entity/LivingEntity;I)V"), cancellable = true)
    private void acornLib$tryAttack(ServerLevel world, Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (target instanceof Player && target.getUUID().equals(acoUuid)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    @Inject(method = "updateAttackTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/brain/task/SonicBoomTask;cooldown(Lnet/minecraft/entity/LivingEntity;I)V"), cancellable = true)
    private void acornLib$updateAttackTarget(LivingEntity target, CallbackInfo ci) {
        if (target instanceof Player && target.getUUID().equals(acoUuid)) {
            ci.cancel();
        }
    }

    @ModifyReturnValue(
            method = "isValidTarget",
            at = @At("RETURN")
    )
    private boolean acornLib$isValidTarget(boolean original, Entity target) {
        return original && !(target instanceof Player && target.getUUID().equals(acoUuid));
    }
}
