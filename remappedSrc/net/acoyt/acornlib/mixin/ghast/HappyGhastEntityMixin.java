package net.acoyt.acornlib.mixin.ghast;

import net.acoyt.acornlib.impl.util.PlushUtils;
import net.acoyt.acornlib.impl.util.interfaces.HappyGhastPlushHolder;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.AcornLib.PLUSH_STACK;

@Mixin(HappyGhast.class)
public abstract class HappyGhastEntityMixin extends LivingEntity implements HappyGhastPlushHolder {
    protected HappyGhastEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public ItemStack acornLib$getPlushStack() {
        return this.entityData.get(PLUSH_STACK);
    }

    @Override
    public void acornLib$setPlushStack(ItemStack plushStack) {
        this.entityData.set(PLUSH_STACK, plushStack);
    }

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    private void plushDataStack(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(PLUSH_STACK, ItemStack.EMPTY);
    }

    @Inject(method = "addPassenger(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private void playPlushHonkSound(Entity passenger, CallbackInfo ci) {
        if (!this.isVehicle() && !this.acornLib$getPlushStack().isEmpty()) {
            this.level().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    PlushUtils.getPlushSound(this.acornLib$getPlushStack()),
                    this.getSoundSource(),
                    1.0F,
                    1.0F
            );
        }
    }
}
