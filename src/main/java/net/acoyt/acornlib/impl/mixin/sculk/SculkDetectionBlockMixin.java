package net.acoyt.acornlib.impl.mixin.sculk;

import net.minecraft.block.BlockState;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.block.SculkShriekerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.util.AcornLibUtils.acoUuid;

@Mixin({SculkSensorBlock.class, SculkShriekerBlock.class})
public class SculkDetectionBlockMixin {
    @Inject(
            method = "onSteppedOn",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornLib$onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            if (player.getUuid().equals(acoUuid)) {
                ci.cancel();
            }
        }
    }
}
