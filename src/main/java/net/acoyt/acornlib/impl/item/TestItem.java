package net.acoyt.acornlib.impl.item;

import net.acoyt.acornlib.api.item.*;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.client.armPose.IArmPose;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.acoyt.acornlib.impl.index.client.AcornArmPoses;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestItem extends Item implements ShieldBreaker, SupporterFeaturesItem, AdvBurningItem, ModelVaryingItem, CustomArmPoseItem {
    public TestItem(Settings settings) {
        super(settings.component(AcornDataComponents.HIT_PARTICLE, HitParticleComponent.DEFAULT));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.literal("Test1\nTest2\nTest3"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (this.isSupporter(user)) {
            user.setCurrentHand(hand);
            user.setVelocity(user.getRotationVector().x * 1.4, user.getRotationVector().y * 1.6, user.getRotationVector().z * 1.4);
        }

        return super.use(world, user, hand);
    }

    public int getShieldCooldown(ItemStack stack) {
        return 53;
    }

    public int getBurnTime(ItemStack stack, LivingEntity attacker, LivingEntity victim) {
        return 4 + victim.getRandom().nextInt(4);
    }

    @Override
    public Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return MiscUtils.isGui(renderMode) ? Identifier.ofVanilla("white_wool") : Identifier.ofVanilla("brick");
    }

    @Override
    public List<Identifier> getModelsToLoad() {
        return List.of();
    }

    @Environment(EnvType.CLIENT)
    public IArmPose getMainHandPose(LivingEntity holder, ItemStack stack) {
        return AcornArmPoses.TEST;
    }

    @Environment(EnvType.CLIENT)
    public IArmPose getOffHandPose(LivingEntity holder, ItemStack stack) {
        return AcornArmPoses.TEST;
    }
}
