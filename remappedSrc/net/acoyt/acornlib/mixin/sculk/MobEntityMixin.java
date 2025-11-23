package net.acoyt.acornlib.mixin.sculk;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.acoyt.acornlib.impl.util.AcornLibUtils.acoUuid;

@Mixin(Mob.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getTarget", at = @At("RETURN"))
    public LivingEntity acornLib$getTarget(LivingEntity target) {
        Mob self = (Mob)(Object)this;
        if (target != null && target.getUUID().equals(acoUuid) && self.getType() == EntityType.WARDEN) {
            return null;
        }
        return target;
    }
}
