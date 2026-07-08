package net.acoyt.acornlib.impl.block;

import com.mojang.serialization.MapCodec;
import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.acoyt.acornlib.impl.index.AcornCriteria;
import net.acoyt.acornlib.impl.util.PlushUtils;
import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
import net.minecraft.world.level.block.*;
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

import java.util.Optional;

/**
 * @author AcoYT
 */
public class PlushBlock extends BaseEntityBlock implements SimpleWaterloggedBlock, LangDiffering<Block> {
    private static final MapCodec<PlushBlock> CODEC = simpleCodec(PlushBlock::new);

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE = box(3.0F, 0.0F, 3.0F, 13.0F, 15.0F, 13.0F);

    public PlushBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
    }

    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.INVISIBLE;
    }

    public void triggerHonk(LivingEntity living) {
        if (living instanceof ServerPlayer serverPlayer) {
            AcornCriteria.HONK.trigger(serverPlayer);
        }
    }

    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof PlushBlockEntity plush) {
            plush.addSquish(24);
        }
    }

    public void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof PlushBlockEntity plush) {
            triggerHonk(player);
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PlushUtils.getPlushSound(state), SoundSource.BLOCKS, 1.0F, 1.0F);
            plush.addSquish(1);
        }

        super.spawnDestroyParticles(level, player, pos, state);
    }

    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof PlushBlockEntity plush) {
            triggerHonk(player);
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PlushUtils.getPlushSound(state), SoundSource.BLOCKS, 1.0F, 1.0F);
            plush.addSquish(1);
        }

        return InteractionResult.SUCCESS;
    }

    public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        BlockPos pos = hit.getBlockPos();
        level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), PlushUtils.getPlushSound(state), SoundSource.BLOCKS, 1.0F, 1.0F);
        if (projectile.getOwner() != null && projectile.getOwner() instanceof LivingEntity living) {
            triggerHonk(living);
        }

        if (level.getBlockEntity(hit.getBlockPos()) instanceof PlushBlockEntity plush) plush.addSquish(1);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level ignoredLevel, BlockState ignoredState, BlockEntityType<T> type) {
        return (level, pos, state, entity) -> {
            if (entity instanceof PlushBlockEntity plushBlock) {
                plushBlock.tick(level, pos, state);
            }
        };
    }

    @Nullable
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

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return state;
    }

    public MutableComponent getName() {
        return getDifferedKey(this)
                .map(st -> Component.translatable(st).withStyle(super.getName().getStyle()))
                .orElse(super.getName());
    }

    public Optional<String> getDifferedKey(Block block) {
        return block == AcornBlocks.CLOWN_ACO_PLUSH || block == AcornBlocks.FESTIVE_ACO_PLUSH
                ? Optional.of("block.acornlib.aco_plush")
                : Optional.empty();
    }
}
