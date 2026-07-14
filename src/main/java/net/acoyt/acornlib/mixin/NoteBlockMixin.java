package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.block.PlushBlock;
import net.acoyt.acornlib.impl.util.PlushUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(NoteBlock.class)
public class NoteBlockMixin {
    @WrapOperation(
            method = "triggerEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;getSoundEvent()Lnet/minecraft/core/Holder;"
            )
    )
    private Holder<SoundEvent> acornlib$replaceNoteBlockSound(NoteBlockInstrument instance, Operation<Holder<SoundEvent>> original, BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return level.getBlockState(pos.above()).getBlock() instanceof PlushBlock
                ? Holder.direct(PlushUtils.getPlushSound(level.getBlockState(pos.above())))
                : original.call(instance);
    }
}
