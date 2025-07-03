package net.acoyt.acornlib.item;

import net.acoyt.acornlib.init.AcornComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class ItemWithSkins extends Item implements SupporterFeaturesItem {
    private final boolean requiresSupporter;
    private final String defaultSkin;

    public ItemWithSkins(Item.Settings settings, boolean requiresSupporter, String defaultSkin) {
        super(settings.component(AcornComponents.SKIN, defaultSkin).maxCount(1));
        this.requiresSupporter = requiresSupporter;
        this.defaultSkin = defaultSkin;
    }

    public static String getSkin(ItemStack stack) {
        return (String)stack.get(AcornComponents.SKIN);
    }

    public static void setSkin(ItemStack stack, String skin) {
        stack.set(AcornComponents.SKIN, skin);
    }

    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof PlayerEntity player) {
            if (this.requiresSupporter && !this.isSupporter(player)) {
                setSkin(stack, defaultSkin);
            }
        }

        super.inventoryTick(stack, world, entity, slot);
    }
}
