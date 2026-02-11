package net.acoyt.acornlib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.Entity;
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

    public static boolean isLookingDown(Entity entity) {
        return entity.getPitch() > 60.2F;
    }

    public static boolean isGui(ModelTransformationMode renderMode) {
        return renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.FIXED;
    }

    public static void ifDev(Runnable runnable) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            runnable.run();
        }
    }

    public static void ifMod(String modId, Runnable runnable) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment() || FabricLoader.getInstance().isModLoaded(modId)) {
            runnable.run();
        }
    }

    public static boolean isPackEnabled(String packId) {
        return MinecraftClient.getInstance().getResourcePackManager().getEnabledIds().contains(packId);
    }

    public static String formatString(String text) {
        if (text == null || text.isEmpty()) return text;

        StringBuilder builder = new StringBuilder();

        boolean formatNext = false;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.toCharArray()[i];
            if (i == 0) {
                ch = Character.toUpperCase(ch);
            } else if (ch == '_' || ch == '/' || ch == '.') {
                ch = ' ';
                formatNext = true;
            } else if (formatNext) {
                ch = Character.toUpperCase(ch);
                formatNext = false;
            }

            builder.append(ch);
        }

        return builder.toString();
    }

    public static String camelCase(String text) {
        if (text == null || text.isEmpty()) return text;

        StringBuilder builder = new StringBuilder();

        boolean formatNext = false;
        for (char ch : text.toCharArray()) {
            if (ch == '_') {
                formatNext = true;
                continue;
            } else if (formatNext) {
                ch = Character.toUpperCase(ch);
                formatNext = false;
            }

            builder.append(ch);
        }

        return builder.toString();
    }

    @Environment(EnvType.CLIENT)
    public static float tickDelta(TickDeltaType type) {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.getRenderTickCounter().getTickDelta(type == TickDeltaType.PAUSE && !client.isPaused());
    }

    public enum TickDeltaType {
        PAUSE,
        DEFAULT
    }
}
