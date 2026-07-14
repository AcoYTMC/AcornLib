package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author AcoYT
 */
@Mixin(FabricLanguageProvider.class)
public abstract class FabricLanguageProviderMixin {
    @WrapOperation(
            //? if > 1.21.11 {
            /*method = "lambda$run$1",
            *///? } else {
            method = "lambda$run$0",
            //? }
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
            /*method = "lambda$run$1",
            *///? } else {
            method = "lambda$run$0",
            //? }
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/TreeMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private static <K, V> V acornlib$ignoreDiffering(TreeMap<K, V> instance, K key, V value, Operation<V> original) {
        return !instance.containsKey(key) ? original.call(instance, key, value) : value;
    }
}
