package net.acoyt.acornlib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author AcoYT
 */
@SuppressWarnings({"unused","deprecation"})
public class MiscUtils {
    public static <C extends Component> ComponentKey<C> getOrCreateKey(ResourceLocation componentId, Class<C> componentClass) {
        return ComponentRegistry.getOrCreate(componentId, componentClass);
    }

    public static void playSoundForNearbyEntities(Level level, Vec3 pos, SoundEvent soundEvent, int range, Predicate<? super LivingEntity> predicate) {
        AABB box = new AABB(pos.x - 1, pos.y - 1, pos.z - 1, pos.x + 1, pos.y + 1, pos.z + 1);
        box = box.inflate(range - 1);

        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, box, predicate)) {
            entity.playSound(soundEvent, 1.0F, 1.0F);
        }
    }

    public static void playSoundForNearbyEntities(Level level, Vec3 pos, SoundEvent soundEvent, int range, Predicate<? super LivingEntity> predicate, float volume, float pitch) {
        AABB box = new AABB(pos.x - 1, pos.y - 1, pos.z - 1, pos.x + 1, pos.y + 1, pos.z + 1);
        box = box.inflate(range - 1);

        for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, box, predicate)) {
            entity.playSound(soundEvent, volume, pitch);
        }
    }

    public static boolean isLookingDown(Entity entity) {
        return entity.getXRot() > 60.2F;
    }

    public static boolean isGui(ItemDisplayContext renderMode) {
        return renderMode == ItemDisplayContext.GROUND || renderMode == ItemDisplayContext.GUI || renderMode == ItemDisplayContext.FIXED;
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
        return Minecraft.getInstance().getResourcePackRepository().getSelectedIds().contains(packId);
    }

    public static boolean isLanguageSelected(String language) {
        return Minecraft.getInstance().getLanguageManager().getSelected().equals(language);
    }

    public static Optional<ModMetadata> getModMetadata(String modId) {
        return FabricLoader.getInstance().getModContainer(modId).map(ModContainer::getMetadata);
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

    public static String formatAfter(String string, char getAfter) {
        if (string == null || string.isEmpty()) return string;

        StringBuilder builder = new StringBuilder();
        boolean continueAfter = false;
        boolean redo = false;
        for (char ch : string.toCharArray()) {
            if (ch == getAfter) {
                if (continueAfter) {
                    redo = true;
                } else {
                    continueAfter = true;
                    continue;
                }
            }

            if (continueAfter) {
                builder.append(ch);
            }
        }

        if (redo) return formatAfter(builder.toString(), getAfter);
        return builder.toString();
    }

    public static String formatCamel(String text) {
        if (text == null || text.isEmpty()) return text;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.toCharArray()[i];
            if (i == 0) {
                builder.append(Character.toUpperCase(ch));
            }

            if (i < text.length() - 1) {
                char next = text.toCharArray()[i + 1];
                if (Character.isUpperCase(next)) {
                    builder.append(' ');
                }

                builder.append(next);
            }
        }

        return builder.toString();
    }

    @Environment(EnvType.CLIENT)
    public static float getTickDelta(boolean ignoreFreeze) {
        Minecraft minecraft = Minecraft.getInstance();
        //? if > 1.21.1 {
        /*return minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(ignoreFreeze);
         *///? } else {
        return minecraft.getTimer().getGameTimeDeltaPartialTick(ignoreFreeze);
        //? }
    }

    @Environment(EnvType.CLIENT)
    public static float getTickDelta() {
        return getTickDelta(false);
    }
}
