package net.acoyt.acornlib.impl.mixin.client;

import net.acoyt.acornlib.impl.index.AcornComponents;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(ItemModelManager.class)
public class ItemModelManagerMixin {
    @Shadow @Final private Function<Identifier, ItemModel> modelGetter;

    @Inject(
            method = "update",
            at = @At("HEAD")
    )
    private void gnarpian$soShiny(ItemRenderState renderState, ItemStack stack, ItemDisplayContext displayContext, World world, LivingEntity entity, int seed, CallbackInfo ci) {
        if (stack != null) {
            if (stack.contains(AcornComponents.SECONDARY_MODEL)) {
                this.modelGetter.apply(stack.getOrDefault(AcornComponents.SECONDARY_MODEL, Identifier.ofVanilla("carrot")))
                        .update(renderState, stack, (ItemModelManager)(Object)this, displayContext, world instanceof ClientWorld clientWorld ? clientWorld : null, entity, seed);
            }

            if (stack.contains(AcornComponents.TERTIARY_MODEL)) {
                this.modelGetter.apply(stack.getOrDefault(AcornComponents.TERTIARY_MODEL, Identifier.ofVanilla("carrot")))
                        .update(renderState, stack, (ItemModelManager)(Object)this, displayContext, world instanceof ClientWorld clientWorld ? clientWorld : null, entity, seed);
            }
        }
    }
}
