package net.acoyt.acornlib.item;

import net.acoyt.acornlib.component.HitParticleComponent;
import net.acoyt.acornlib.init.AcornComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TestItem extends Item implements ShieldBreaker, SupporterFeaturesItem {
    public TestItem(Item.Settings settings) {
        super(settings
                .component(AcornComponents.HIT_PARTICLE, HitParticleComponent.DEFAULT));
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
}
