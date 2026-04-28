package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;

/**
 * @author AcoYT
 */
@SuppressWarnings("deprecation")
public class BlockEntityTypeRegistrant extends RegistrantBase<BlockEntityType<?>> {
    public BlockEntityTypeRegistrant(String modId) {
        super(modId, Registries.BLOCK_ENTITY_TYPE);
    }

    public <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder<T> builder) {
        BlockEntityType<T> blockEntityType = Registry.register(Registries.BLOCK_ENTITY_TYPE, id(name), builder.build());
        this.toRegister.add(blockEntityType);
        return blockEntityType;
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // BlockEntityTypes don't have lang
    }
}
