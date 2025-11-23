package net.acoyt.acornlib.impl.block;

import com.mojang.serialization.MapCodec;
import net.acoyt.acornlib.impl.init.AcornBlockEntities;
import net.acoyt.acornlib.impl.init.AcornCriterions;
import net.acoyt.acornlib.impl.util.PlushUtils;
import net.minecraft.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class PlushBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    private static final MapCodec<PlushBlock> CODEC = simpleCodec(PlushBlock::new);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = box(3.0F, 0.0F, 3.0F, 13.0F, 15.0F, 13.0F);

    public PlushBlock(Properties settings) {
        super(settings);
    }
    
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    public void triggerHonk(LivingEntity living) {
        if (living instanceof ServerPlayer serverPlayer) {
            AcornCriterions.HONK.trigger(serverPlayer);
        }
    }

    public void attack(BlockState state, Level world, BlockPos pos, Player player) {
        if (!world.isClientSide && world.getBlockEntity(pos) instanceof PlushBlockEntity plush) {
            plush.squish(24);
        }
    }

    public void spawnDestroyParticles(Level world, Player player, BlockPos pos, BlockState state) {
        triggerHonk(player);
        if (world.getBlockEntity(pos) instanceof PlushBlockEntity plush) plush.squish(1);

        super.spawnDestroyParticles(world, player, pos, state);
    }

    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide) {
            triggerHonk(player);
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PlushUtils.getPlushSound(state), SoundSource.BLOCKS, 1.0F, 1.0F);

            if (world.getBlockEntity(pos) instanceof PlushBlockEntity plush) plush.squish(1);
        }

        return InteractionResult.SUCCESS;
    }

    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        BlockPos pos = hit.getBlockPos();
        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PlushUtils.getPlushSound(state), SoundSource.BLOCKS, 1.0F, 1.0F);
        if (projectile.getOwner() instanceof LivingEntity living) {
            triggerHonk(living);
        }

        if (world.getBlockEntity(pos) instanceof PlushBlockEntity plush) plush.squish(1);
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, AcornBlockEntities.PLUSH, PlushBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PlushBlockEntity(pos, state);
    }

    public BlockState getStateForPlacement(@NotNull BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidState.is(Fluids.WATER));
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return state;
    }
}