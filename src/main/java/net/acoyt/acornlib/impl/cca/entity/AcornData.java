package net.acoyt.acornlib.impl.cca.entity;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

//? if > 1.21.1 {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///? } else {
import net.minecraft.nbt.CompoundTag;
//? }

/**
 * @author AcoYT
 */
@SuppressWarnings("ALL")
public class AcornData implements AutoSyncedComponent {
    public static final ComponentKey<AcornData> KEY = ComponentRegistry.getOrCreate(AcornLib.id("data"), AcornData.class);
    private final Player player;
    public String storedPerspective = "FIRST_PERSON";

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

    public AcornData(Player player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    //? if > 1.21.1 {
    /*public void readData(ValueInput view) {
        this.storedPerspective = view.getStringOr("StoredPerspective", "FIRST_PERSON");

        this.overlays = view.getBooleanOr("Overlays", true);
        this.crosshair = view.getBooleanOr("Crosshair", true);
        this.hotbar = view.getBooleanOr("Hotbar", true);
        this.armor = view.getBooleanOr("Armor", true);
        this.health = view.getBooleanOr("Health", true);
        this.hunger = view.getBooleanOr("Hunger", true);
        this.bubbles = view.getBooleanOr("Bubbles", true);
        this.experience = view.getBooleanOr("Experience", true);
        this.effects = view.getBooleanOr("Effects", true);
        this.bossbar = view.getBooleanOr("Bossbar", true);
        this.debugHud = view.getBooleanOr("DebugHud", true);
        this.sidebar = view.getBooleanOr("Sidebar", true);
        this.titles = view.getBooleanOr("Titles", true);
        this.chat = view.getBooleanOr("Chat", true);
        this.players = view.getBooleanOr("Players", true);
        this.subtitles = view.getBooleanOr("Subtitles", true);
        this.tooltip = view.getBooleanOr("Tooltip", true);
    }

    public void writeData(ValueOutput view) {
        view.putString("StoredPerspective", this.storedPerspective);

        view.putBoolean("Overlays", this.overlays);
        view.putBoolean("Crosshair", this.crosshair);
        view.putBoolean("Hotbar", this.hotbar);
        view.putBoolean("Armor", this.armor);
        view.putBoolean("Health", this.health);
        view.putBoolean("Hunger", this.hunger);
        view.putBoolean("Bubbles", this.bubbles);
        view.putBoolean("Experience", this.experience);
        view.putBoolean("Effects", this.effects);
        view.putBoolean("Bossbar", this.bossbar);
        view.putBoolean("DebugHud", this.debugHud);
        view.putBoolean("Sidebar", this.sidebar);
        view.putBoolean("Titles", this.titles);
        view.putBoolean("Chat", this.chat);
        view.putBoolean("Players", this.players);
        view.putBoolean("Subtitles", this.subtitles);
        view.putBoolean("Tooltip", this.tooltip);
    }
    *///? } else {
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider registries) {
        this.storedPerspective = tag.getString("StoredPerspective");

        this.overlays = tag.getBoolean("Overlays");
        this.crosshair = tag.getBoolean("Crosshair");
        this.hotbar = tag.getBoolean("Hotbar");
        this.armor = tag.getBoolean("Armor");
        this.health = tag.getBoolean("Health");
        this.hunger = tag.getBoolean("Hunger");
        this.bubbles = tag.getBoolean("Bubbles");
        this.experience = tag.getBoolean("Experience");
        this.effects = tag.getBoolean("Effects");
        this.bossbar = tag.getBoolean("Bossbar");
        this.debugHud = tag.getBoolean("DebugHud");
        this.sidebar = tag.getBoolean("Sidebar");
        this.titles = tag.getBoolean("Titles");
        this.chat = tag.getBoolean("Chat");
        this.players = tag.getBoolean("Players");
        this.subtitles = tag.getBoolean("Subtitles");
        this.tooltip = tag.getBoolean("Tooltip");
    }

    public void writeToNbt(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putString("StoredPerspective", this.storedPerspective);

        tag.putBoolean("Overlays", this.overlays);
        tag.putBoolean("Crosshair", this.crosshair);
        tag.putBoolean("Hotbar", this.hotbar);
        tag.putBoolean("Armor", this.armor);
        tag.putBoolean("Health", this.health);
        tag.putBoolean("Hunger", this.hunger);
        tag.putBoolean("Bubbles", this.bubbles);
        tag.putBoolean("Experience", this.experience);
        tag.putBoolean("Effects", this.effects);
        tag.putBoolean("Bossbar", this.bossbar);
        tag.putBoolean("DebugHud", this.debugHud);
        tag.putBoolean("Sidebar", this.sidebar);
        tag.putBoolean("Titles", this.titles);
        tag.putBoolean("Chat", this.chat);
        tag.putBoolean("Players", this.players);
        tag.putBoolean("Subtitles", this.subtitles);
        tag.putBoolean("Tooltip", this.tooltip);
    }
    //? }

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
