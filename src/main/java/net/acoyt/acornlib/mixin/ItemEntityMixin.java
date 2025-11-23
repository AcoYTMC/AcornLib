package net.acoyt.acornlib.mixin;

import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow public abstract ItemStack getStack();

    @Shadow @Nullable public abstract Entity getOwner();

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    public void tick(CallbackInfo ci) {
        ItemStack stack = this.getStack();
        Entity owner = this.getOwner();
        if (stack.contains(AcornComponents.UNDROPPABLE)) {
            if (!this.getWorld().isClient && owner instanceof PlayerEntity player) {
                player.giveItemStack(stack);
                this.kill(this.getServer().getWorld(this.getWorld().getRegistryKey()));
            }
        }
    }

    @Inject(
            method = "cannotPickup",
            at = @At("RETURN"),
            cancellable = true
    )
    public void cannotPickup(CallbackInfoReturnable<Boolean> cir) {
        if (this.getStack().contains(AcornComponents.UNDROPPABLE)) {
            cir.setReturnValue(false);
        }
    }
}
