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

@Mixin(NoteBlock.class)
public class NoteBlockMixin {
    @WrapOperation(
            method = "onSyncedBlockEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/enums/NoteBlockInstrument;getSound()Lnet/minecraft/registry/entry/RegistryEntry;"
            )
    )
    private Holder<SoundEvent> acornLib$plushieReplaceNoteBlockSound(NoteBlockInstrument instance, Operation<Holder<SoundEvent>> original, BlockState state, @NotNull Level world, @NotNull BlockPos pos) {
        return world.getBlockState(pos.above()).getBlock() instanceof PlushBlock ? Holder.direct(PlushUtils.getPlushSound(world.getBlockState(pos.above()))) : original.call(instance);
    }
}
