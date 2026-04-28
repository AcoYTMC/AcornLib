package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class ItemGroupRegistrant extends RegistrantBase<ItemGroup> {
    public ItemGroupRegistrant(String modId) {
        super(modId, Registries.ITEM_GROUP);
    }

    public ItemGroup register(String name, ItemGroup itemGroup, ItemGroupEvents.ModifyEntries entries) {
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP, id(name))).register(entries);
        return super.register(name, itemGroup);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(group -> {
            Identifier id = getId(group).withPath(st -> "itemGroup." + st);
            builder.add(id.getPath(), formatString(id.getPath()));
        });
    }
}
