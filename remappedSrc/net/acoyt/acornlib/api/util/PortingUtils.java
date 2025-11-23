package net.acoyt.acornlib.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

@SuppressWarnings("unused")
public class PortingUtils {
    public static BlockPos toBlockPos(CompoundTag nbt) {
        return new BlockPos(nbt.getIntOr("X", 0), nbt.getIntOr("Y", 0), nbt.getIntOr("Z", 0));
    }

    public static CompoundTag fromBlockPos(BlockPos pos) {
        CompoundTag nbtCompound = new CompoundTag();
        nbtCompound.putInt("X", pos.getX());
        nbtCompound.putInt("Y", pos.getY());
        nbtCompound.putInt("Z", pos.getZ());
        return nbtCompound;
    }
}
