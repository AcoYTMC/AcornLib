package net.acoyt.acornlib.api.registrants;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.component.ComponentType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.function.UnaryOperator;

/**
 * @author AcoYT
 */
public class EnchantmentEffectRegistrant extends RegistrantBase<ComponentType<?>> {
    public EnchantmentEffectRegistrant(String modId) {
        super(modId, Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE);
    }

    public <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        ComponentType<T> componentType = builderOperator.apply(ComponentType.builder()).build();
        register(name, componentType);
        return componentType;
    }

    public <T> ComponentType<T> register(String id, Codec<T> codec, PacketCodec<? super RegistryByteBuf, T> packetCodec) {
        ComponentType<T> componentType = ComponentType.<T>builder()
                .codec(codec)
                .packetCodec(packetCodec)
                .build();

        register(id, componentType);
        return componentType;
    }

    @Deprecated
    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // ComponentTypes don't have lang
    }
}
