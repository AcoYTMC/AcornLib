package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(ItemModelResolver.class)
public class ItemModelManagerMixin {
    @Shadow @Final private Function<ResourceLocation, ItemModel> modelGetter;

    @Inject(
            method = "update",
            at = @At("HEAD")
    )
    private void gnarpian$soShiny(ItemStackRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, Level world, LivingEntity entity, int seed, CallbackInfo ci) {
        if (stack != null) {
            if (stack.has(AcornComponents.SECONDARY_MODEL)) {
                this.modelGetter.apply(stack.getOrDefault(AcornComponents.SECONDARY_MODEL, ResourceLocation.withDefaultNamespace("carrot")))
                        .update(renderState, stack, (ItemModelResolver)(Object)this, displayContext, world instanceof ClientLevel clientWorld ? clientWorld : null, entity, seed);
            }

            if (stack.has(AcornComponents.TERTIARY_MODEL)) {
                this.modelGetter.apply(stack.getOrDefault(AcornComponents.TERTIARY_MODEL, ResourceLocation.withDefaultNamespace("carrot")))
                        .update(renderState, stack, (ItemModelResolver)(Object)this, displayContext, world instanceof ClientLevel clientWorld ? clientWorld : null, entity, seed);
            }
        }
    }
}
