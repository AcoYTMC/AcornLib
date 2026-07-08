package net.acoyt.acornlib.api.registrants;

//~ if > 1.21.11 'itemgroup.v1.ItemGroupEvents' -> 'creativetab.v1.CreativeModeTabEvents' {
//~ if > 1.21.11 'ItemGroupEvents' -> 'CreativeModeTabEvents' {
//~ if > 1.21.11 'ModifyEntries' -> 'ModifyOutput' {
//~ if > 1.21.11 'modifyEntriesEvent' -> 'modifyOutputEvent' {
import net.acoyt.acornlib.api.template.RegistrantBase;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.locale.Language;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.*;
import java.util.function.Function;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class ItemRegistrant extends RegistrantBase<Item> {
    public final Map<ItemLike, List<ResourceKey<CreativeModeTab>>> toGroup = new HashMap<>();

    public ItemRegistrant(String modId) {
        super(modId, BuiltInRegistries.ITEM);
    }

    //? if > 1.21.3 {
    public Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        Item item = Items.registerItem(ResourceKey.create(Registries.ITEM, this.id(name)), factory, properties);
        this.toRegister.add(item);
        return item;
    }
    //? } else {
    /*public Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        Item item = Registry.register(BuiltInRegistries.ITEM, this.id(name), factory.apply(properties));
        this.toRegister.add(item);
        return item;
    }
    *///? }

    public Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties, ResourceKey<CreativeModeTab>[] groups) {
        Item item = this.register(name, factory, properties);
        this.toGroup.put(item, Arrays.asList(groups));
        return item;
    }

    public void registerToGroups() {
        this.toGroup.forEach((item, keys) -> {
            for (ResourceKey<CreativeModeTab> itemGroup : keys) {
                ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.accept(item));
            }
        });
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(item -> {
            if (item instanceof LangDiffering differing) {
                Optional<String> key = differing.getDifferedKey(item);
                String translationKey = key.orElse(item.getDescriptionId());
                Identifier id = getId(item);
                if (!Language.getInstance().has(translationKey)) {
                    builder.add(key.orElse(item.getDescriptionId()), formatString(id.getPath()));
                }
            } else {
                Identifier id = getId(item);
                builder.add(item, formatString(id.getPath()));
            }
        });
    }
}
//~}
//~}
//~}
//~}