package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * @author AcoYT
 */
public class ParticleTypeRegistrant extends RegistrantBase<ParticleType<?>> {
    public ParticleTypeRegistrant(String modId) {
        super(modId, BuiltInRegistries.PARTICLE_TYPE);
    }

    public <T extends ParticleType<?>> T register(String name, T particle) {
        T entry = Registry.register(BuiltInRegistries.PARTICLE_TYPE, id(name), particle);
        this.toRegister.add(entry);
        return entry;
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        // ParticleTypes don't have lang
    }
}
