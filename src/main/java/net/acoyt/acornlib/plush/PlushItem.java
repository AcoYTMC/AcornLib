package net.acoyt.acornlib.plush;

import net.acoyt.acornlib.init.AcornBlocks;
import net.acoyt.acornlib.util.ColoredText;
import net.acoyt.acornlib.util.PlushUtils;
import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class PlushItem extends BlockItem implements ProjectileItem {
    public PlushItem(Block block, Item.Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        world.playSound(
                (PlayerEntity)null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_ARROW_SHOOT,
                SoundCategory.NEUTRAL,
                0.5F,
                1.5F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!world.isClient) {
            ThrownPlushEntity plush = new ThrownPlushEntity(user, world, stack, null, PlushUtils.getPlushBlock(stack));
            plush.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(plush);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.isCreative()) {
            stack.decrement(1);
        }

        return super.use(world, user, hand);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new ThrownPlushEntity(pos.getX(), pos.getY(), pos.getZ(), world, stack, null, PlushUtils.getPlushBlock(stack));
    }

    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> tooltip, TooltipType type) {
        if (stack.isOf(AcornBlocks.ACO_PLUSH.asItem())) {
            tooltip.accept(ColoredText.withColor(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD), 0x8D78CD));
        }

        if (stack.isOf(AcornBlocks.FESTIVE_ACO_PLUSH.asItem())) {
            tooltip.accept(ColoredText.withColor(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD), 0xD54DAB));
        }

        if (stack.isOf(AcornBlocks.CLOWN_ACO_PLUSH.asItem())) {
            tooltip.accept(ColoredText.withColor(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD), 0x1B84C4));
        }

        if (stack.isOf(AcornBlocks.KIO_PLUSH.asItem())) {
            tooltip.accept(ColoredText.withColor(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD), 0x1d171d));
        }

    }
}
