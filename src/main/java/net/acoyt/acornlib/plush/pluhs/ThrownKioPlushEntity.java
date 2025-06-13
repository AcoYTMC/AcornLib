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

public class ThrownKioPlushEntity extends ThrownPlushEntity {
    public ThrownKioPlushEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrownKioPlushEntity(LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack shotFrom, Block block) {
        super(AcornEntities.THROWN_KIO_PLUSH, owner, world, stack, shotFrom, block);
    }

    public ThrownKioPlushEntity(double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon, Block block) {
        super(AcornEntities.THROWN_KIO_PLUSH, x, y, z, world, stack, weapon, block);
    }

    public ItemStack getDefaultItemStack() {
        return AcornBlocks.KIO_PLUSH.asItem().getDefaultStack();
    }
}
