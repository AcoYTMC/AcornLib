package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.acoyt.acornlib.impl.item.TranslationBlockItem;
import net.acoyt.acornlib.impl.util.Util;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class BlockRegistrant extends RegistrantBase<Block> {
    public final Map<ItemConvertible, List<RegistryKey<ItemGroup>>> toGroup = new HashMap<>();

    public BlockRegistrant(String modId) {
        super(modId, Registries.BLOCK);
    }

    public Block register(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return register(name, block);
    }

    public Block registerWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        Util.register(this.id(name), itemSettings -> new TranslationBlockItem(block, itemSettings), new Item.Settings());
        return register(name, block);
    }

    public Block registerWithItem(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, BiFunction<Block, Item.Settings, Item> itemFactory) {
        Block block = blockFactory.apply(settings);
        Util.register(this.id(name), itemSettings -> itemFactory.apply(block, itemSettings), new Item.Settings());
        return register(name, block);
    }

    public Block registerWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, RegistryKey<ItemGroup>[] groups) {
        Block block = factory.apply(settings);
        Item item = Util.register(this.id(name), itemSettings -> new TranslationBlockItem(block, itemSettings), new Item.Settings());
        this.toGroup.put(item, Arrays.asList(groups));
        return register(name, block);
    }

    public void registerToGroups() {
        this.toGroup.forEach((item, keys) -> {
            for (RegistryKey<ItemGroup> itemGroup : keys) {
                ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(item));
            }
        });
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(block -> {
            if (block instanceof LangDiffering differing) {
                Optional<String> key = differing.getDifferedKey(block);
                Identifier id = getId(block);
                builder.add(key.orElse(block.getTranslationKey()), formatString(id.getPath()));
            } else {
                Identifier id = getId(block);
                builder.add(block, formatString(id.getPath()));
            }
        });
    }
}
