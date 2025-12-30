package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.CanConsumeItemEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "canConsume", at = @At("HEAD"), cancellable = true)
    private void noEat(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack stack = player.getActiveItem();
        Boolean original = cir.getReturnValue();
        if (original != null && stack != null) {
            boolean bl = CanConsumeItemEvent.EVENT.invoker().canConsume(player, stack);
            cir.setReturnValue(bl && original);
        }
    }
}
