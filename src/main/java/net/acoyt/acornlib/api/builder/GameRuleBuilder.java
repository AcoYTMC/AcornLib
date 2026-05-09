package net.acoyt.acornlib.api.builder;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.GameRules.Category;
import net.minecraft.world.GameRules.Key;
import net.minecraft.world.GameRules.Rule;
import net.minecraft.world.GameRules.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
public class GameRuleBuilder {
    public final List<String> gameRuleIds = new ArrayList<>();

    public <T extends Rule<T>> Key<T> register(String id, Category category, Type<T> value) {
        this.gameRuleIds.add(id);
        return GameRuleRegistry.register(id, category, value);
    }

    public <T extends Rule<T>> Key<T> register(String id, CustomGameRuleCategory category, Type<T> value) {
        this.gameRuleIds.add(id);
        return GameRuleRegistry.register(id, category, value);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.gameRuleIds.forEach(id -> builder.add("gamerule." + id, MiscUtils.formatCamel(id)));
    }
}
