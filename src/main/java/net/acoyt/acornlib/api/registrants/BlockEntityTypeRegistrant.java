package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

@SuppressWarnings("deprecation")
public class BlockEntityTypeRegistrant extends RegistrantBase<BlockEntityType<?>> {
    public BlockEntityTypeRegistrant(String modId) {
        super(modId, Registries.BLOCK_ENTITY_TYPE);
    }

    public BlockEntityType<?> register(String name, FabricBlockEntityTypeBuilder<?> builder) {
        return register(name, builder.build());
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // BlockEntityTypes don't have lang
    }
}
