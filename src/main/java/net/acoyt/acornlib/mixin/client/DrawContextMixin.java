package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.impl.util.OrderedTextCharacterVisitor;
import net.acoyt.acornlib.mixin.access.OrderedTextToolTipAccessor;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.OrderedTextTooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin {
    @Shadow public abstract int getScaledWindowWidth();

    @WrapOperation(
            method = "drawItem(Lnet/minecraft/entity/LivingEntity;" +
                    "Lnet/minecraft/world/World;" +
                    "Lnet/minecraft/item/ItemStack;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemRenderer;renderItem(Lnet/minecraft/item/ItemStack;" +
                            "Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V"
            )
    )
    private void acornlib$wrapForFix(ItemRenderer instance, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, Operation<Void> original, @Local(argsOnly = true) @Nullable LivingEntity entity) {
        if (stack.getItem() instanceof ModelVaryingItem varyingItem) {
            Identifier identifier = varyingItem.getModel(renderMode, stack, entity);
            original.call(instance, stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, instance.getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(identifier)));
            return;
        }

        original.call(instance, stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, model);
    }

    @ModifyVariable(
            method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;" +
                    "Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V",
            at = @At("HEAD"),
            index = 2,
            argsOnly = true
    )
    public List<TooltipComponent> acornlib$makeTooltipMutable(List<TooltipComponent> value) {
        return new ArrayList<>(value);
    }

    @Inject(
            method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;" +
                    "Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V",
            at = @At("HEAD")
    )
    public void acornlib$fixTooltipDrawing(TextRenderer textRenderer, List<TooltipComponent> components, int x, int y, TooltipPositioner positioner, CallbackInfo ci) {
        int width = getScaledWindowWidth();

        int forcedWidth = 0;
        for (TooltipComponent component : components) {
            if (!(component instanceof OrderedTextTooltipComponent)) {
                int width2 = component.getWidth(textRenderer);
                if (width2 > forcedWidth) {
                    forcedWidth = width2;
                }
            }
        }

        int maxWidth = width - 20 - x;
        if (forcedWidth > maxWidth || maxWidth < 100) {
            maxWidth = x - 28;
        }

        for (int i = 0; i < components.size(); i++) {
            if (components.get(i) instanceof OrderedTextTooltipComponent tooltipComponent) {
                Text text = OrderedTextCharacterVisitor.get(((OrderedTextToolTipAccessor) tooltipComponent).acornlib$getText());
                if (text.getSiblings().isEmpty()) continue;

                List<TooltipComponent> wrapped = textRenderer.wrapLines(text, maxWidth).stream().map(TooltipComponent::of).toList();
                components.remove(i);
                components.addAll(i, wrapped);
            }
        }
    }
}
