package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.block.PlushBlock;
import net.acoyt.acornlib.impl.util.PlushUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
    private RegistryEntry<SoundEvent> acornLib$plushieReplaceNoteBlockSound(NoteBlockInstrument instance, Operation<RegistryEntry<SoundEvent>> original, BlockState state, @NotNull World world, @NotNull BlockPos pos) {
        return world.getBlockState(pos.up()).getBlock() instanceof PlushBlock ? RegistryEntry.of(PlushUtils.getPlushSound(world.getBlockState(pos.up()))) : original.call(instance);
    }
}
