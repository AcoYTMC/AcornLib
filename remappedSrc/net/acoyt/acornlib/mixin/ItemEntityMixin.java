package net.acoyt.acornlib.mixin;

import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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

    public ItemEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    public void tick(CallbackInfo ci) {
        ItemStack stack = this.getStack();
        Entity owner = this.getOwner();
        if (stack.has(AcornComponents.UNDROPPABLE)) {
            if (!this.level().isClientSide && owner instanceof Player player) {
                player.addItem(stack);
                this.kill(this.getServer().getLevel(this.level().dimension()));
            }
        }
    }

    @Inject(
            method = "cannotPickup",
            at = @At("RETURN"),
            cancellable = true
    )
    public void cannotPickup(CallbackInfoReturnable<Boolean> cir) {
        if (this.getStack().has(AcornComponents.UNDROPPABLE)) {
            cir.setReturnValue(false);
        }
    }
}
