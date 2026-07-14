package net.acoyt.acornlib.mixin;

import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.server.level.ServerLevel;
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

/**
 * @author AcoYT
 */
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow public abstract ItemStack getItem();
    @Shadow @Nullable public abstract Entity getOwner();

    public ItemEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void acornlib$tickUndroppableLogic(CallbackInfo ci) {
        ItemStack stack = this.getItem();
        Entity owner = this.getOwner();
        if (stack.has(AcornDataComponents.UNDROPPABLE)) {
            if (!this.level().isClientSide() && owner instanceof Player player) {
                player.addItem(stack);
                this.kill((ServerLevel) this.level());
            }
        }
    }

    @Inject(method = "hasPickUpDelay", at = @At("RETURN"), cancellable = true)
    public void acornlib$cannotPickup(CallbackInfoReturnable<Boolean> cir) {
        if (this.getItem().has(AcornDataComponents.UNDROPPABLE)) {
            cir.setReturnValue(false);
        }
    }
}
