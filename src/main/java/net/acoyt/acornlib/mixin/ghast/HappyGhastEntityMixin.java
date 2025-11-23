package net.acoyt.acornlib.mixin.ghast;

import net.acoyt.acornlib.impl.util.PlushUtils;
import net.acoyt.acornlib.impl.util.interfaces.HappyGhastPlushHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.AcornLib.PLUSH_STACK;

@Mixin(HappyGhastEntity.class)
public abstract class HappyGhastEntityMixin extends LivingEntity implements HappyGhastPlushHolder {
    protected HappyGhastEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack acornLib$getPlushStack() {
        return this.dataTracker.get(PLUSH_STACK);
    }

    @Override
    public void acornLib$setPlushStack(ItemStack plushStack) {
        this.dataTracker.set(PLUSH_STACK, plushStack);
    }

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    private void plushDataStack(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(PLUSH_STACK, ItemStack.EMPTY);
    }

    @Inject(method = "addPassenger(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private void playPlushHonkSound(Entity passenger, CallbackInfo ci) {
        if (!this.hasPassengers() && !this.acornLib$getPlushStack().isEmpty()) {
            this.getWorld().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    PlushUtils.getPlushSound(this.acornLib$getPlushStack()),
                    this.getSoundCategory(),
                    1.0F,
                    1.0F
            );
        }
    }
}
