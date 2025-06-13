package net.acoyt.acornlib.plush;

import net.acoyt.acornlib.init.AcornBlocks;
import net.acoyt.acornlib.util.PlushUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public abstract class ThrownPlushEntity extends PersistentProjectileEntity {
    private Block block;

    public ThrownPlushEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownPlushEntity(EntityType<? extends PersistentProjectileEntity> type, double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(type, x, y, z, world, stack, weapon);
        this.setStack(stack);
        this.setBlock(PlushUtils.getPlushBlock(stack));
    }

    public ThrownPlushEntity(EntityType<? extends PersistentProjectileEntity> type, LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(type, owner, world, stack, shotFrom);
        this.setStack(stack);
        this.setBlock(PlushUtils.getPlushBlock(stack));
    }

    public ThrownPlushEntity(EntityType<? extends ThrownPlushEntity> entityType, double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon, Block block) {
        super(entityType, x, y, z, world, stack, weapon);
        this.setStack(stack);
        this.setBlock(block);
    }

    public ThrownPlushEntity(EntityType<? extends ThrownPlushEntity> entityType, LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack shotFrom, Block block) {
        super(entityType, owner, world, stack, shotFrom);
        this.setStack(stack);
        this.setBlock(block);
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            if (this.getWorld() instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 8; ++i) {
                    serverWorld.spawnParticles(particleEffect, this.getX(), this.getY(), this.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    private ParticleEffect getParticleParameters() {
        ItemStack stack = PlushUtils.getPlushItem(this.getBlock()).getDefaultStack();
        return new ItemStackParticleEffect(ParticleTypes.ITEM, !stack.isEmpty() ? stack : AcornBlocks.ACO_PLUSH.asItem().getDefaultStack());
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.dropItem(serverWorld, PlushUtils.getPlushItem(this.getBlock()));
            this.getWorld().playSound((PlayerEntity)null, this.getBlockPos(), PlushUtils.getPlushSound(this), SoundCategory.NEUTRAL, 1.0F, 1.0F);
            this.discard();
        }
    }
}
