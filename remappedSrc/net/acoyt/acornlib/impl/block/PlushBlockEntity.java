package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.init.AcornBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlushBlockEntity extends BlockEntity {
    public double squish;

    public PlushBlockEntity(BlockPos pos, BlockState state) {
        super(AcornBlockEntities.PLUSH, pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, @NotNull PlushBlockEntity plush) {
        if (plush.squish > 0.0) {
            plush.squish /= 3.0;
            if (plush.squish < 0.01) {
                plush.squish = 0.0;
                if (world != null) {
                    world.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
                }
            }
        }

        if (world != null && world.getBlockEntity(pos.below()) instanceof JukeboxBlockEntity jukebox) {
            JukeboxSongPlayer manager = jukebox.getSongPlayer();
            if (manager.isPlaying() && manager.getTicksSinceSongStarted() % 7 == 0) {
                plush.squish(1);
            }
        }
    }

    public void squish(int squish) {
        this.squish += squish;
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
        }

        this.setChanged();
    }

    @Override
    protected void saveAdditional(ValueOutput view) {
        super.saveAdditional(view);
        view.putDouble("squish", this.squish);
    }

    @Override
    protected void loadAdditional(ValueInput view) {
        super.loadAdditional(view);
        this.squish = view.getDoubleOr("squish", 0);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
