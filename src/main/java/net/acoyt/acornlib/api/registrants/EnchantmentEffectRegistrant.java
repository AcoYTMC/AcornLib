package net.acoyt.acornlib.api.registrants;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.function.UnaryOperator;

/**
 * @author AcoYT
 */
public class EnchantmentEffectRegistrant extends RegistrantBase<DataComponentType<?>> {
    public EnchantmentEffectRegistrant(String modId) {
        super(modId, BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE);
    }

    public <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        DataComponentType<T> componentType = builderOperator.apply(DataComponentType.builder()).build();
        this.register(name, componentType);
        return componentType;
    }

    public <T> DataComponentType<T> register(String id, Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> packetCodec) {
        DataComponentType<T> componentType = DataComponentType.<T>builder()
                .persistent(codec)
                .networkSynchronized(packetCodec)
                .build();

        this.register(id, componentType);
        return componentType;
    }

    @Deprecated
    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        // ComponentTypes don't have lang
    }
}
