package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.CanConsumeItemEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author AcoYT
 */
@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "canEat", at = @At("HEAD"), cancellable = true)
    private void acornlib$noEat(boolean canAlwaysEat, CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player)(Object)this;
        ItemStack stack = player.getUseItem();
        Boolean original = cir.getReturnValue();
        if (original != null && stack != null) {
            boolean bl = CanConsumeItemEvent.EVENT.invoker().canConsume(player, stack);
            cir.setReturnValue(bl && original);
        }
    }
}
