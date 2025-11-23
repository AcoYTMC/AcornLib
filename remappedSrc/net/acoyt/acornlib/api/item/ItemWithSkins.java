package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class ItemWithSkins extends Item implements SupporterFeaturesItem {
    public final boolean requiresSupporter;
    public final String defaultSkin;

    public ItemWithSkins(Properties settings, boolean requiresSupporter, String defaultSkin) {
        super(settings.component(AcornComponents.SKIN, defaultSkin).stacksTo(1));
        this.requiresSupporter = requiresSupporter;
        this.defaultSkin = defaultSkin;
    }

    public ItemWithSkins(Properties settings, boolean requiresSupporter) {
        super(settings.component(AcornComponents.SKIN, "default").stacksTo(1));
        this.requiresSupporter = requiresSupporter;
        this.defaultSkin = "default";
    }

    @Override
    public boolean isSupporter(Player player) {
        return !this.requiresSupporter || AcornLib.isSupporter(player);
    }

    public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof Player player) {
            if (this.requiresSupporter && !this.isSupporter(player)) {
                setSkin(stack, defaultSkin);
            }
        }

        super.inventoryTick(stack, world, entity, slot);
    }
}
