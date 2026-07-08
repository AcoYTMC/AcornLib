package net.acoyt.acornlib.impl.client.layer;

//? if > 1.21.5 {
import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.impl.client.addon.HappyGhastRenderStateAddon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
//? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
@Environment(EnvType.CLIENT)
public class HappyGhastPlushLayer<M extends HappyGhastModel> extends RenderLayer<HappyGhastRenderState, M> {
    private final ItemModelResolver itemModelManager;

    public HappyGhastPlushLayer(RenderLayerParent<HappyGhastRenderState, M> context) {
        super(context);
        this.itemModelManager = Minecraft.getInstance().getItemModelResolver();
    }

    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int light, HappyGhastRenderState state, float yRot, float xRot) {
        HappyGhastRenderStateAddon addon = HappyGhastRenderStateAddon.get(state);
        ItemStack plushStack = addon.plushStack;

        if (!plushStack.isEmpty()) {
            poseStack.pushPose();
            poseStack.translate(0, -3, 0.3);
            poseStack.scale(2.0F, 2.0F, 2.0F);

            ItemStackRenderState itemRenderState = new ItemStackRenderState();
            this.itemModelManager.appendItemLayers(itemRenderState, plushStack, ItemDisplayContext.FIXED, addon.level, addon.entity, 0);
            itemRenderState.submit(poseStack, collector, light, OverlayTexture.NO_OVERLAY, 0);

            poseStack.popPose();
        }
    }
}
//? }