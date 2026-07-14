package net.acoyt.acornlib.mixin.access;

import net.acoyt.acornlib.impl.util.interfaces.ItemStackAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if > 1.21.1
//import net.minecraft.world.entity.EquipmentSlot;

/**
 * @author AcoYT
 */
@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackAccess {
    @Unique private LivingEntity stackHolder;

    @Override
    public void setStackHolder(LivingEntity stackHolder) {
        this.stackHolder = stackHolder;
    }

    @Override
    public LivingEntity getStackHolder() {
        return this.stackHolder;
    }

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    //? if > 1.21.1 {
    /*private void acornLib$updateStackHolder(Level level, Entity owner, EquipmentSlot slot, CallbackInfo ci) {
     *///? } else {
    private void acornLib$updateStackHolder(Level level, Entity owner, int slot, boolean selected, CallbackInfo ci) {
        //? }
        if (owner instanceof LivingEntity living) {
            this.setStackHolder(living);
        }
    }
}
