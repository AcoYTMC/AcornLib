package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

//? if <= 26.1.2 {
/*import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
*///? }

/**
 * @author AcoYT
 */
@SuppressWarnings("ALL")
@Mixin(FabricLanguageProvider.class)
public abstract class FabricLanguageProviderMixin {
    //? if > 26.1.2 {
    @WrapOperation(
            method = "lambda$run$0",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/fabricmc/fabric/api/datagen/v1/provider/FabricLanguageProvider;generateTranslations(Lnet/minecraft/core/HolderLookup$Provider;Lnet/fabricmc/fabric/api/datagen/v1/provider/FabricLanguageProvider$TranslationBuilder;)V"
            )
    )
    private void acornlib$ignoreDiffering(FabricLanguageProvider instance, HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder, Operation<Void> original,
                                          @Local(name = "translationEntries") TreeMap<String, String> entries) {
        List<String> differed = new ArrayList<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            if (block instanceof LangDiffering differing) {
                Optional<String> translationKey = differing.getDifferedKey(block);
                translationKey.ifPresent(differed::add);
            }
        }

        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof LangDiffering differing) {
                Optional<String> translationKey = differing.getDifferedKey(item);
                translationKey.ifPresent(differed::add);
            }
        }

        original.call(instance, provider, new FabricLanguageProvider.TranslationBuilder() {
            @Override
            public boolean has(String key) {
                return builder.has(key) && !differed.contains(key);
            }

            @Override
            public @Nullable String overwrite(String key, String value) {
                return !entries.containsKey(key) ? builder.overwrite(key, value) : value;
            }
        });
    }
    //? } else {
    /*@WrapOperation(
            //? if > 1.21.11 {
            method = "lambda$run$1",
            //? } else {
            /^method = "lambda$run$0",
            ^///? }
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/TreeMap;containsKey(Ljava/lang/Object;)Z"
            )
    )
    private static <K, V> boolean acornlib$ignoreDiffering(TreeMap<K, V> instance, K key, Operation<Boolean> original) {
        List<String> differed = new ArrayList<>();
        for (Block block : BuiltInRegistries.BLOCK) {
            if (block instanceof LangDiffering differing) {
                Optional<String> translationKey = differing.getDifferedKey(block);
                translationKey.ifPresent(differed::add);
            }
        }

        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof LangDiffering differing) {
                Optional<String> translationKey = differing.getDifferedKey(item);
                translationKey.ifPresent(differed::add);
            }
        }

        return original.call(instance, key) && !differed.contains(key);
    }

    @WrapOperation(
            //? if > 1.21.11 {
            method = "lambda$run$1",
            //? } else {
            /^method = "lambda$run$0",
            ^///? }
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/TreeMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private static <K, V> V acornlib$ignoreDiffering(TreeMap<K, V> instance, K key, V value, Operation<V> original) {
        return !instance.containsKey(key) ? original.call(instance, key, value) : value;
    }
    *///? }
}
