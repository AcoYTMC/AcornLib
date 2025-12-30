package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.init.AcornBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.block.jukebox.JukeboxManager;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlushBlockEntity extends BlockEntity {
    public double squish = 0;

    public PlushBlockEntity(BlockPos pos, BlockState state) {
        super(AcornBlockEntities.PLUSH, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, @NotNull PlushBlockEntity plush) {
        if (plush.squish > 0.0) {
            plush.squish /= 3.0;
            if (plush.squish < 0.01) {
                plush.squish = 0.0;
                if (world != null) {
                    world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
                }
            }
        }

        if (world != null && world.getBlockEntity(pos.down()) instanceof JukeboxBlockEntity jukebox) {
            JukeboxManager manager = jukebox.getManager();
            if (manager.isPlaying() && manager.getTicksSinceSongStarted() % 7 == 0) {
                plush.squish(1);
            }
        }
    }

    public void squish(int squish) {
        this.squish += squish;
        if (this.world != null) {
            this.world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
        }

        this.markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putDouble("squish", this.squish);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.squish = nbt.getDouble("squish");
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }
}
