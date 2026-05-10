package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
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
            method = "lambda$run$0",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/TreeMap;containsKey(Ljava/lang/Object;)Z"
            )
    )
    private static <K, V> boolean acornlib$ignoreDiffering(TreeMap<K, V> instance, K key, Operation<Boolean> original) {
        List<String> differed = new ArrayList<>();
        for (Block block : Registries.BLOCK) {
            if (block instanceof LangDiffering differing) {
                Optional<String> translationKey = differing.getDifferedKey(block);
                translationKey.ifPresent(differed::add);
            }
        }

        for (Item item : Registries.ITEM) {
            if (item instanceof LangDiffering differing) {
                Optional<String> translationKey = differing.getDifferedKey(item);
                translationKey.ifPresent(differed::add);
            }
        }

        return original.call(instance, key) && !differed.contains(key);
    }

    @WrapOperation(
            method = "lambda$run$0",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/TreeMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private static <K, V> V acornlib$ignoreDiffering(TreeMap<K, V> instance, K key, V value, Operation<V> original) {
        return !instance.containsKey(key) ? original.call(instance, key, value) : value;
    }
}
