package net.acoyt.acornlib.api.registrants;

//~ if > 1.21.11 'itemgroup.v1.ItemGroupEvents' -> 'creativetab.v1.CreativeModeTabEvents' {
//~ if > 1.21.11 'ItemGroupEvents' -> 'CreativeModeTabEvents' {
//~ if > 1.21.11 'ModifyEntries' -> 'ModifyOutput' {
//~ if > 1.21.11 'modifyEntriesEvent' -> 'modifyOutputEvent' {
import net.acoyt.acornlib.api.template.RegistrantBase;
import net.acoyt.acornlib.impl.block.TranslationBlockItem;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

//? if > 1.21.3 {
/*import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
*///? } else {
import net.minecraft.core.Registry;
//? }

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class BlockRegistrant extends RegistrantBase<Block> {
    public final Map<ItemLike, List<ResourceKey<CreativeModeTab>>> toGroup = new HashMap<>();

    public BlockRegistrant(String modId) {
        super(modId, BuiltInRegistries.BLOCK);
    }

    //? if > 1.21.3 {
    /*public Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = Blocks.register(ResourceKey.create(Registries.BLOCK, this.id(name)), factory, properties);
        this.toRegister.add(block);
        return block;
    }

    public Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = this.register(name, factory, properties);
        Items.registerItem(ResourceKey.create(Registries.ITEM, this.id(name)), itemSettings -> new BlockItem(block, itemSettings), new Item.Properties().useBlockDescriptionPrefix());
        return block;
    }

    public Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, BiFunction<Block, Item.Properties, Item> itemFactory) {
        Block block = this.register(name, blockFactory, properties);
        Items.registerItem(ResourceKey.create(Registries.ITEM, this.id(name)), itemSettings -> itemFactory.apply(block, itemSettings), new Item.Properties());
        return block;
    }

    public Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, ResourceKey<CreativeModeTab>[] groups) {
        Block block = this.register(name, factory, properties);
        Item item = Items.registerItem(ResourceKey.create(Registries.ITEM, this.id(name)), itemSettings -> new BlockItem(block, itemSettings), new Item.Properties().useBlockDescriptionPrefix());
        this.toGroup.put(item, Arrays.asList(groups));
        return block;
    }
    *///? } else {
    public Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = Registry.register(BuiltInRegistries.BLOCK, this.id(name), factory.apply(properties));
        this.toRegister.add(block);
        return block;
    }

    public Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = this.register(name, factory, properties);
        Registry.register(BuiltInRegistries.ITEM, this.id(name), new TranslationBlockItem(block, new Item.Properties()));
        return block;
    }

    public Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, BiFunction<Block, Item.Properties, Item> itemFactory) {
        Block block = this.register(name, blockFactory, properties);
        Registry.register(BuiltInRegistries.ITEM, this.id(name), itemFactory.apply(block, new Item.Properties()));
        return block;
    }

    public Block registerWithItem(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, ResourceKey<CreativeModeTab>[] groups) {
        Block block = this.register(name, factory, properties);
        Item item = Registry.register(BuiltInRegistries.ITEM, this.id(name), itemSettings -> new TranslationBlockItem(block, new Item.Properties()));
        this.toGroup.put(item, Arrays.asList(groups));
        return block;
    }
    //? }

    public void registerToGroups() {
        this.toGroup.forEach((item, keys) -> {
            for (ResourceKey<CreativeModeTab> itemGroup : keys) {
                ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.accept(item));
            }
        });
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(block -> {
            if (block instanceof LangDiffering differing) {
                Optional<String> key = differing.getDifferedKey(block);
                String translationKey = key.orElse(block.getDescriptionId());
                ResourceLocation id = getId(block);
                if (!Language.getInstance().has(translationKey)) {
                    builder.add(key.orElse(block.getDescriptionId()), formatString(id.getPath()));
                }
            } else {
                ResourceLocation id = getId(block);
                builder.add(block, formatString(id.getPath()));
            }
        });
    }
}
//~}
//~}
//~}
//~}