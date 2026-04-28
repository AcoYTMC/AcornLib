package net.acoyt.acornlib.impl.cca.entity;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author AcoYT
 */
public class AcornData implements AutoSyncedComponent {
    public static final ComponentKey<AcornData> KEY = ComponentRegistry.getOrCreate(AcornLib.id("data"), AcornData.class);
    private final PlayerEntity player;
    private String storedPerspective = "FIRST_PERSON";

    public boolean overlays = true;
    public boolean crosshair = true;
    public boolean hotbar = true;
    public boolean armor = true;
    public boolean health = true;
    public boolean hunger = true;
    public boolean bubbles = true;
    public boolean experience = true;
    public boolean effects = true;
    public boolean bossbar = true;
    public boolean debugHud = true;
    public boolean sidebar = true;
    public boolean titles = true;
    public boolean chat = true;
    public boolean players = true;
    public boolean subtitles = true;
    public boolean tooltip = true;

    public AcornData(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        this.storedPerspective = nbt.getString("StoredPerspective");

        this.overlays = nbt.getBoolean("Overlays");
        this.crosshair = nbt.getBoolean("Crosshair");
        this.hotbar = nbt.getBoolean("Hotbar");
        this.armor = nbt.getBoolean("Armor");
        this.health = nbt.getBoolean("Health");
        this.hunger = nbt.getBoolean("Hunger");
        this.bubbles = nbt.getBoolean("Bubbles");
        this.experience = nbt.getBoolean("Experience");
        this.effects = nbt.getBoolean("Effects");
        this.bossbar = nbt.getBoolean("Bossbar");
        this.debugHud = nbt.getBoolean("DebugHud");
        this.sidebar = nbt.getBoolean("Sidebar");
        this.titles = nbt.getBoolean("Titles");
        this.chat = nbt.getBoolean("Chat");
        this.players = nbt.getBoolean("Players");
        this.subtitles = nbt.getBoolean("Subtitles");
        this.tooltip = nbt.getBoolean("Tooltip");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        nbt.putString("StoredPerspective", this.storedPerspective);

        nbt.putBoolean("Overlays", this.overlays);
        nbt.putBoolean("Crosshair", this.crosshair);
        nbt.putBoolean("Hotbar", this.hotbar);
        nbt.putBoolean("Armor", this.armor);
        nbt.putBoolean("Health", this.health);
        nbt.putBoolean("Hunger", this.hunger);
        nbt.putBoolean("Bubbles", this.bubbles);
        nbt.putBoolean("Experience", this.experience);
        nbt.putBoolean("Effects", this.effects);
        nbt.putBoolean("Bossbar", this.bossbar);
        nbt.putBoolean("DebugHud", this.debugHud);
        nbt.putBoolean("Sidebar", this.sidebar);
        nbt.putBoolean("Titles", this.titles);
        nbt.putBoolean("Chat", this.chat);
        nbt.putBoolean("Players", this.players);
        nbt.putBoolean("Subtitles", this.subtitles);
        nbt.putBoolean("Tooltip", this.tooltip);
    }

    public void setAll(boolean value) {
        this.overlays = value;
        this.crosshair = value;
        this.hotbar = value;
        this.armor = value;
        this.health = value;
        this.hunger = value;
        this.bubbles = value;
        this.experience = value;
        this.effects = value;
        this.bossbar = value;
        this.debugHud = value;
        this.sidebar = value;
        this.titles = value;
        this.chat = value;
        this.players = value;
        this.subtitles = value;
        this.tooltip = value;
        this.sync();
    }

    public String getStoredPerspective() {
        return this.storedPerspective;
    }

    public void setStoredPerspective(String storedPerspective) {
        this.storedPerspective = storedPerspective;
        this.sync();
    }
}
