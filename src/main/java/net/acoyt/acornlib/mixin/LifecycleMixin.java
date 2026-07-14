package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.Lifecycle;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author AcoYT
 */
@Mixin(value = Lifecycle.class, remap = false, priority = 2000)
public class LifecycleMixin {
    @Shadow @Final private static Lifecycle STABLE;

    @WrapMethod(method = "experimental")
    private static Lifecycle acornlib$disableExperimentalWarningInDev(Operation<Lifecycle> original) {
        Lifecycle lifecycle = original.call();
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? STABLE : lifecycle;
    }
}
