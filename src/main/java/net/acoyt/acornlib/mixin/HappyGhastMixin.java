//? if > 1.21.5 {
/*package net.acoyt.acornlib.mixin;

import net.acoyt.acornlib.impl.cca.entity.HappyGhastPlushData;
import net.acoyt.acornlib.impl.util.PlushUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends LivingEntity {
    protected HappyGhastMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "addPassenger(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"))
    private void acornlib$playPlushSoundOnRide(Entity passenger, CallbackInfo ci) {
        HappyGhastPlushData plushData = HappyGhastPlushData.KEY.get(this);
        if (!this.isVehicle() && !plushData.plushStack.isEmpty()) {
            this.level().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    PlushUtils.getPlushSound(plushData.plushStack),
                    this.getSoundSource(),
                    1.0F,
                    1.0F
            );
        }
    }
}
*///? }