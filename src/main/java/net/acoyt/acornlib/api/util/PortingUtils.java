package net.acoyt.acornlib.api.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

@SuppressWarnings("unused")
public class PortingUtils {
    public static BlockPos toBlockPos(NbtCompound nbt) {
        return new BlockPos(nbt.getInt("X", 0), nbt.getInt("Y", 0), nbt.getInt("Z", 0));
    }

    public static NbtCompound fromBlockPos(BlockPos pos) {
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putInt("X", pos.getX());
        nbtCompound.putInt("Y", pos.getY());
        nbtCompound.putInt("Z", pos.getZ());
        return nbtCompound;
    }
}
