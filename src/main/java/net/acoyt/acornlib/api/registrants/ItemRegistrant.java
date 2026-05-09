package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.Function;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class ItemRegistrant extends RegistrantBase<Item> {
    public final Map<ItemConvertible, List<RegistryKey<ItemGroup>>> toGroup = new HashMap<>();

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

    public Item register(String name, Function<Item.Settings, Item> factory, Item.Settings settings, RegistryKey<ItemGroup>[] groups) {
        Item item = register(name, factory, settings);
        this.toGroup.put(item, Arrays.asList(groups));
        return item;
    }

    public void registerToGroups() {
        this.toGroup.forEach((item, keys) -> {
            for (RegistryKey<ItemGroup> itemGroup : keys) {
                ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
            }
        });
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(item -> {
            if (item instanceof LangDiffering differing) {
                Optional<String> key = differing.getDifferedKey(item);
                Identifier id = getId(item);
                builder.add(key.orElse(item.getTranslationKey()), formatString(id.getPath()));
            } else {
                Identifier id = getId(item);
                builder.add(item, formatString(id.getPath()));
            }
        });
    }
}
