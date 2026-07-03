package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
@SuppressWarnings("unused")
public class ItemWithSkins extends Item implements SupporterFeaturesItem {
    public final List<String> skins = new ArrayList<>();
    public final boolean requiresSupporter;
    public final String defaultSkin;

    public ItemWithSkins(Properties properties, boolean requiresSupporter, String defaultSkin) {
        super(properties.component(AcornDataComponents.SKIN, defaultSkin).stacksTo(1));
        this.requiresSupporter = requiresSupporter;
        this.defaultSkin = defaultSkin;
        this.skins.add(defaultSkin);
    }

    public ItemWithSkins(Properties properties, boolean requiresSupporter) {
        this(properties, requiresSupporter, "default");
    }

    public String getSkin(@NotNull ItemStack stack) {
        return stack.getOrDefault(AcornDataComponents.SKIN, this.defaultSkin);
    }

    public String getNextSkin(String current) {
        if (!this.skins.contains(current)) return this.defaultSkin;
        int ordinal = this.skins.indexOf(current);
        return this.skins.get((ordinal + 1) % this.skins.size());
    }

    public boolean isSupporter(Player player) {
        return !this.requiresSupporter || AcornLib.isSupporter(player);
    }

    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof Player player) {
            if (this.requiresSupporter && !this.isSupporter(player)) {
                setSkin(stack, this.defaultSkin);
            }
        }

        super.inventoryTick(stack, level, entity, slot);
    }
}
