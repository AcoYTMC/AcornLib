package net.acoyt.acornlib.impl.block;

import com.mojang.serialization.MapCodec;
import net.acoyt.acornlib.impl.init.AcornBlockEntities;
import net.acoyt.acornlib.impl.init.AcornCriterions;
import net.acoyt.acornlib.impl.util.PlushUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlushBlock extends BlockWithEntity implements Waterloggable {
    private static final MapCodec<PlushBlock> CODEC = createCodec(PlushBlock::new);

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = createCuboidShape(3.0F, 0.0F, 3.0F, 13.0F, 15.0F, 13.0F);

    public PlushBlock(Settings settings) {
        super(settings);
    }

    public MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return super.getRenderType(state);
    }

    public void triggerHonk(LivingEntity living) {
        if (living instanceof ServerPlayerEntity serverPlayer) {
            AcornCriterions.HONK.trigger(serverPlayer);
        }
    }

    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!world.isClient && world.getBlockEntity(pos) instanceof PlushBlockEntity plush) {
            plush.squish(24);
        }
    }

    public void spawnBreakParticles(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        triggerHonk(player);
        if (world.getBlockEntity(pos) instanceof PlushBlockEntity plush) plush.squish(1);

        super.spawnBreakParticles(world, player, pos, state);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            triggerHonk(player);
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PlushUtils.getPlushSound(state), SoundCategory.BLOCKS, 1.0F, 1.0F);

            if (world.getBlockEntity(pos) instanceof PlushBlockEntity plush) plush.squish(1);
        }

        return ActionResult.SUCCESS;
    }

    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos pos = hit.getBlockPos();
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PlushUtils.getPlushSound(state), SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (projectile.getOwner() != null && projectile.getOwner() instanceof LivingEntity living) {
            triggerHonk(living);
        }

        if (world.getBlockEntity(hit.getBlockPos()) instanceof PlushBlockEntity plush) plush.squish(1);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, AcornBlockEntities.PLUSH, PlushBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PlushBlockEntity(pos, state);
    }

    public BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(WATERLOGGED, fluidState.isOf(Fluids.WATER));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return state;
    }
}