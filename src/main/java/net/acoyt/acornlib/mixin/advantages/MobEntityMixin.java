package net.acoyt.acornlib.mixin.advantages;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.acoyt.acornlib.impl.util.Util.hasAdvantages;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getTarget", at = @At("RETURN"))
    public LivingEntity acornlib$getTarget(LivingEntity target) {
        if (hasAdvantages(target) && this.getType() == EntityType.WARDEN) {
            return null;
        }

        return target;
    }
}
