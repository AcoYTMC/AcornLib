package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.world.entity.LivingEntity;

/**
 * @author AcoYT
 */
public interface ItemStackAccess {
    LivingEntity getStackHolder();
    void setStackHolder(LivingEntity living);
}
