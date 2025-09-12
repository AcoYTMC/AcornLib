package net.acoyt.acornlib.api.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class MiscUtils {
    public static void playSoundForNearbyEntities(World world, Vec3d pos, SoundEvent soundEvent, int range, Predicate<? super LivingEntity> predicate) {
        Box box = new Box(pos.x - 1, pos.y - 1, pos.z - 1, pos.x + 1, pos.y + 1, pos.z + 1);
        box = box.expand(range - 1);

        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, box, predicate)) {
            entity.playSound(soundEvent, 1.0F, 1.0F);
        }
    }

    public static void playSoundForNearbyEntities(World world, Vec3d pos, SoundEvent soundEvent, int range, Predicate<? super LivingEntity> predicate, float volume, float pitch) {
        Box box = new Box(pos.x - 1, pos.y - 1, pos.z - 1, pos.x + 1, pos.y + 1, pos.z + 1);
        box = box.expand(range - 1);

        for (LivingEntity entity : world.getEntitiesByClass(LivingEntity.class, box, predicate)) {
            entity.playSound(soundEvent, volume, pitch);
        }
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static float closerTo(float value, float thisFloat, float thatFloat) {
        return Math.abs(value - thisFloat) < Math.abs(value - thatFloat) ? thisFloat : thatFloat;
    }
}
