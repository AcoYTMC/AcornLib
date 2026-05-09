package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, id(name), (builderOperator.apply(ComponentType.builder()).build()));
    }

    @Deprecated
    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // ComponentTypes don't have lang
    }
}
