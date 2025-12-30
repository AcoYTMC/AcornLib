package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ItemWithSkins extends Item implements SupporterFeaturesItem {
    public final List<String> skins = new ArrayList<>();
    public final boolean requiresSupporter;
    public final String defaultSkin;

    public ItemWithSkins(Settings settings, boolean requiresSupporter, String defaultSkin) {
        super(settings.component(AcornComponents.SKIN, defaultSkin).maxCount(1));
        this.requiresSupporter = requiresSupporter;
        this.defaultSkin = defaultSkin;
        this.skins.add(defaultSkin);
    }

    public ItemWithSkins(Settings settings, boolean requiresSupporter) {
        super(settings.component(AcornComponents.SKIN, "default").maxCount(1));
        this.requiresSupporter = requiresSupporter;
        this.defaultSkin = "default";
        this.skins.add("default");
    }

    @Override
    public boolean isSupporter(PlayerEntity player) {
        return !this.requiresSupporter || AcornLib.isSupporter(player);
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            if (this.requiresSupporter && !this.isSupporter(player)) {
                setSkin(stack, this.defaultSkin);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
