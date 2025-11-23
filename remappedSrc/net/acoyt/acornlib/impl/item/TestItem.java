package net.acoyt.acornlib.impl.item;

import net.acoyt.acornlib.api.item.AdvBurningItem;
import net.acoyt.acornlib.api.item.ShieldBreaker;
import net.acoyt.acornlib.api.item.SupporterFeaturesItem;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TestItem extends Item implements ShieldBreaker, SupporterFeaturesItem, AdvBurningItem {
    public TestItem(Properties settings) {
        super(settings.component(AcornComponents.HIT_PARTICLE, HitParticleComponent.DEFAULT));
    }

    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (this.isSupporter(user)) {
            user.startUsingItem(hand);
            user.setDeltaMovement(user.getLookAngle().x * 1.4, user.getLookAngle().y * 1.6, user.getLookAngle().z * 1.4);
        }

        return InteractionResult.PASS;
    }

    public float shieldCooldown(ItemStack stack) {
        return 5.3F;
    }

    public int getBurnTime(ItemStack stack, LivingEntity attacker, LivingEntity victim) {
        return 4 + victim.getRandom().nextInt(4);
    }
}
