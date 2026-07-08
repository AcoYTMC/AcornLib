package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.index.AcornBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

//? if > 1.21.1 {
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
//? }

/**
 * @author AcoYT
 */
public class PlushBlockEntity extends BlockEntity {
    private double squish = 0.0;

    public PlushBlockEntity(BlockPos pos, BlockState state) {
        super(AcornBlockEntities.PLUSH, pos, state);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level == null) return;

        if (squish > 0) {
            squish = Mth.clamp(squish /= 3.0, 0.0, squish);
            if (squish == 0.0) {
                level.sendBlockUpdated(pos, state, state, Block.UPDATE_CLIENTS);
            }
        }

        if (level.getBlockEntity(pos.below()) instanceof JukeboxBlockEntity jukebox) {
            JukeboxSongPlayer player = jukebox.getSongPlayer();
            if (player.isPlaying() && player.getTicksSinceSongStarted() % 7 == 0) {
                addSquish(1);
            }
        }
    }

    public void sendBlockUpdated() {
        this.setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_INVISIBLE);
        }
    }

    public double getSquish() {
        return squish;
    }

    public void setSquish(double squish) {
        this.squish = squish;
        sendBlockUpdated();
    }

    public void addSquish(int squish) {
        setSquish(this.squish + squish);
    }

    //? if > 1.21.1 {
    public void saveAdditional(ValueOutput view) {
        view.putDouble("Squish", squish);
    }

    public void loadAdditional(ValueInput view) {
        squish = view.getDoubleOr("Squish", 0.0);
    }
    //? } else {
    /*public void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putDouble("Squish", squish);
    }

    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        squish = tag.getDouble("Squish");
    }
    *///? }

    @Nullable
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return saveWithoutMetadata(provider);
    }
}
