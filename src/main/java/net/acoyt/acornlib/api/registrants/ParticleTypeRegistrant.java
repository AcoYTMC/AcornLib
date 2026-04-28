package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;

/**
 * @author AcoYT
 */
public class ParticleTypeRegistrant extends RegistrantBase<ParticleType<?>> {
    public ParticleTypeRegistrant(String modId) {
        super(modId, Registries.PARTICLE_TYPE);
    }

    public <T extends ParticleType<?>> T create(String name, T particle) {
        this.toRegister.add(particle);
        return Registry.register(Registries.PARTICLE_TYPE, id(name), particle);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // ParticleTypes don't have lang
    }
}
