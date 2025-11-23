package net.acoyt.acornlib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.function.Predicate;

public class MiscUtils {
    public static void playSoundForNearbyEntities(Level world, Vec3 pos, SoundEvent soundEvent, int range, Predicate<? super LivingEntity> predicate) {
        AABB box = new AABB(pos.x - 1, pos.y - 1, pos.z - 1, pos.x + 1, pos.y + 1, pos.z + 1);
        box = box.inflate(range - 1);

        for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, box, predicate)) {
            entity.playSound(soundEvent, 1.0F, 1.0F);
        }
    }

    public static void playSoundForNearbyEntities(Level world, Vec3 pos, SoundEvent soundEvent, int range, Predicate<? super LivingEntity> predicate, float volume, float pitch) {
        AABB box = new AABB(pos.x - 1, pos.y - 1, pos.z - 1, pos.x + 1, pos.y + 1, pos.z + 1);
        box = box.inflate(range - 1);

        for (LivingEntity entity : world.getEntitiesOfClass(LivingEntity.class, box, predicate)) {
            entity.playSound(soundEvent, volume, pitch);
        }
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static float closerTo(float value, float thisFloat, float thatFloat) {
        return Math.abs(value - thisFloat) < Math.abs(value - thatFloat) ? thisFloat : thatFloat;
    }

    @Environment(EnvType.CLIENT)
    public static float tickDelta(TickDeltaType type) {
        Minecraft client = Minecraft.getInstance();
        return client.getDeltaTracker().getGameTimeDeltaPartialTick(type == TickDeltaType.PAUSE && !client.isPaused());
    }

    public enum TickDeltaType {
        PAUSE,
        DEFAULT
    }
}
