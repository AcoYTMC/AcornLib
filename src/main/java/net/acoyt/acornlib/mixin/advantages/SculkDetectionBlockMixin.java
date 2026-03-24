package net.acoyt.acornlib.mixin.advantages;

import net.minecraft.block.BlockState;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.block.SculkShriekerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.util.Util.hasAdvantages;

@Mixin({SculkSensorBlock.class, SculkShriekerBlock.class})
public class SculkDetectionBlockMixin {
    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    private void acornlib$onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (hasAdvantages(entity)) {
            ci.cancel();
        }
    }
}
