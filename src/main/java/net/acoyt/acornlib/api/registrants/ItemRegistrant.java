package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

public class ItemRegistrant extends RegistrantBase<Item> {
    public ItemRegistrant(String modId) {
        super(modId, Registries.ITEM);
    }

    public Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return register(name, item);
    }

    public Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings, RegistryKey<ItemGroup>... groups) {
        Item item = register(name, factory, settings);
        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register(b -> b.add(item));
        }

        return item;
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(item -> {
            Identifier id = getId(item);
            builder.add(item, formatString(id.getPath()));
        });
    }
}
