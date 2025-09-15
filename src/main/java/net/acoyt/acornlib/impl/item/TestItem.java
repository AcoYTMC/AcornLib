package net.acoyt.acornlib.impl.item;

import net.acoyt.acornlib.api.item.AdvBurningItem;
import net.acoyt.acornlib.api.item.ShieldBreaker;
import net.acoyt.acornlib.api.item.SupporterFeaturesItem;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TestItem extends Item implements ShieldBreaker, SupporterFeaturesItem, AdvBurningItem {
    public TestItem(Settings settings) {
        super(settings.component(AcornComponents.HIT_PARTICLE, HitParticleComponent.DEFAULT));
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (this.isSupporter(user)) {
            user.setCurrentHand(hand);
            user.setVelocity(user.getRotationVector().x * 1.4, user.getRotationVector().y * 1.6, user.getRotationVector().z * 1.4);
        }

        return ActionResult.PASS;
    }

    public float shieldCooldown() {
        return 5.3F;
    }

    public int getBurnTime(ItemStack stack, LivingEntity attacker, LivingEntity victim) {
        return 4 + victim.getRandom().nextInt(4);
    }
}
