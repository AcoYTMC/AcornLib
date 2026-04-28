package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class BlockRegistrant extends RegistrantBase<Block> {
    public BlockRegistrant(String modId) {
        super(modId, Registries.BLOCK);
    }

    public Block register(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return register(name, block);
    }

    public Block registerWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        Registry.register(Registries.ITEM, id(name), new TranslationBlockItem(block, new Item.Settings()));
        return register(name, block);
    }

    public Block registerWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, RegistryKey<ItemGroup>... groups) {
        Block block = factory.apply(settings);
        Item item = Registry.register(Registries.ITEM, id(name), new TranslationBlockItem(block, new Item.Settings()));
        for (RegistryKey<ItemGroup> group : groups) {
            ItemGroupEvents.modifyEntriesEvent(group).register(e -> e.add(item));
        }

        return register(name, block);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(block -> {
            Identifier id = getId(block);
            builder.add(block, formatString(id.getPath()));
        });
    }
}
