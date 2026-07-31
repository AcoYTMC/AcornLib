package net.acoyt.acornlib.api.registrants;

//? if > 1.21.10 {
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;

import java.util.function.ToIntFunction;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;
//? }

/**
 * @author AcoYT
 */
//? if > 1.21.10 {
public class GameRuleRegistrant extends RegistrantBase<GameRule<?>> {
    public GameRuleRegistrant(String modId) {
        super(modId, BuiltInRegistries.GAME_RULE);
    }

    public GameRule<Boolean> registerBooleanRule(String name, GameRuleCategory category, boolean defaultValue) {
        return register(name, category, GameRuleType.BOOL, BoolArgumentType.bool(), Codec.BOOL, defaultValue, FeatureFlagSet.of(), GameRuleTypeVisitor::visitBoolean, value -> value ? 1 : 0);
    }

    public GameRule<Integer> registerIntRule(String name, GameRuleCategory category, int defaultValue, int minValue) {
        return registerIntRule(name, category, defaultValue, minValue, Integer.MAX_VALUE, FeatureFlagSet.of());
    }

    public GameRule<Integer> registerIntRule(String name, GameRuleCategory category, int defaultValue, int minValue, int maxValue) {
        return registerIntRule(name, category, defaultValue, minValue, maxValue, FeatureFlagSet.of());
    }

    public GameRule<Integer> registerIntRule(String name, GameRuleCategory category, int defaultValue, int minValue, int maxValue, FeatureFlagSet requiredFeatures) {
        return register(name, category, GameRuleType.INT, IntegerArgumentType.integer(minValue, maxValue), Codec.intRange(minValue, maxValue), defaultValue, requiredFeatures, GameRuleTypeVisitor::visitInteger, value -> value);
    }

    public <T> GameRule<T> register(String name, GameRuleCategory category, GameRuleType type, ArgumentType<T> argumentType, Codec<T> codec, T defaultValue, FeatureFlagSet requiredFeatures, GameRules.VisitorCaller<T> acceptor, ToIntFunction<T> commandResultSupplier) {
        return this.register(name, new GameRule<>(category, type, argumentType, acceptor, codec, commandResultSupplier, defaultValue, requiredFeatures));
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(rule -> {
            Identifier id = this.getId(rule);
            builder.add("gamerule." + id.getNamespace() + "." + id.getPath(), formatString(id.getPath()));
        });
    }
}
//? }