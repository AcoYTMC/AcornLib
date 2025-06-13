package net.acoyt.acornlib.cca;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.NotNull;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * Stores values for telling if a player is holding attack or use.
 * Also provides the amount of time they have been held.
 */
public class HoldingComponent implements AutoSyncedComponent, CommonTickingComponent {
    private final PlayerEntity player;
    private boolean attacking = false;
    private boolean using = false;
    private int tickAttacking = 0;
    private int tickUsing = 0;

    public HoldingComponent(PlayerEntity player) {
        this.player = player;
    }

    public static HoldingComponent get(PlayerEntity player) {
        return AcornEntityComponents.HOLDING.get(player);
    }

    public void sync() {
        AcornEntityComponents.HOLDING.sync(this.player);
    }

    public boolean isAttacking() {
        return this.attacking;
    }

    public boolean isUsing() {
        return this.using;
    }

    public int getAttackTicks() {
        return this.tickAttacking;
    }

    public int getUsageTicks() {
        return this.tickUsing;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        this.sync();
    }

    public void setUsing(boolean using) {
        this.using = using;
        this.sync();
    }

    public void tick() {
        if (this.attacking) {
            this.tickAttacking++;
        } else {
            this.tickAttacking = 0;
        }

        if (this.using) {
            this.tickUsing++;
        } else {
            this.tickUsing = 0;
        }
    }

    public void readFromNbt(@NotNull NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.attacking = tag.getBoolean("using", false);
        this.using = tag.getBoolean("using", false);
        this.tickAttacking = tag.getInt("tickAttacking", 0);
        this.tickUsing = tag.getInt("tickUsing", 0);
    }

    public void writeToNbt(@NotNull NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putBoolean("using", this.attacking);
        tag.putBoolean("using", this.using);
        tag.putInt("tickAttacking", this.tickAttacking);
        tag.putInt("tickUsing", this.tickUsing);
    }

    public @Override boolean isRequiredOnClient() {
        return false;
    }
}
