package net.acoyt.acornlib.plush.pluhs;

import net.acoyt.acornlib.init.AcornBlocks;
import net.acoyt.acornlib.init.AcornEntities;
import net.acoyt.acornlib.plush.ThrownPlushEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ThrownAcoPlushEntity extends ThrownPlushEntity {
    public ThrownAcoPlushEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownAcoPlushEntity(LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack shotFrom, Block block) {
        super(AcornEntities.THROWN_ACO_PLUSH, owner, world, stack, shotFrom, block);
    }

    public ThrownAcoPlushEntity(double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon, Block block) {
        super(AcornEntities.THROWN_ACO_PLUSH, x, y, z, world, stack, weapon, block);
    }

    public ItemStack getDefaultItemStack() {
        return AcornBlocks.ACO_PLUSH.asItem().getDefaultStack();
    }
}
