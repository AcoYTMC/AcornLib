package net.acoyt.acornlib.impl.event;

//? if > 1.21.5 {
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.cca.entity.HappyGhastPlushData;
import net.acoyt.acornlib.impl.index.tag.AcornItemTags;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
//? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
public class EquipHappyGhastPlushEvent implements UseEntityCallback {
    public InteractionResult interact(Player player, Level level, InteractionHand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        ItemStack stack = player.getMainHandItem();
        if (player.isShiftKeyDown() && AcornLib.isSupporter(player) && entity instanceof HappyGhast happyGhast && !level.isClientSide()) {
            if (!happyGhast.isVehicle()) {
                HappyGhastPlushData plushData = HappyGhastPlushData.KEY.get(happyGhast);
                ItemStack plushStack = plushData.plushStack;
                Vec3 pos = player.position();

                if (stack.is(AcornItemTags.PLUSHIES) && plushStack.getItem() != stack.getItem() && plushData.fallback == 0) {
                    ItemStack itemStack = plushStack.copyWithCount(1);
                    if (!itemStack.isEmpty()) Containers.dropItemStack(level, pos.x, pos.y, pos.z, itemStack);

                    plushData.plushStack = stack.copyWithCount(1);
                    plushData.fallback = 5;
                    plushData.sync();

                    stack.consume(1, player);

                    level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.HARNESS_EQUIP.value(), SoundSource.NEUTRAL, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }

                if (stack.isEmpty() && !plushStack.isEmpty() && plushData.fallback == 0) {
                    ItemStack itemStack = plushStack.copyWithCount(1);
                    if (!itemStack.isEmpty()) Containers.dropItemStack(level, pos.x, pos.y, pos.z, itemStack);

                    plushData.plushStack = ItemStack.EMPTY;
                    plushData.fallback = 5;
                    plushData.sync();

                    level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.HARNESS_UNEQUIP, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
//? }