/*
package net.acoyt.acornlib.api.template;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.mixin.access.LevelRendererAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.irisshaders.iris.Iris;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
*/

/**
 * @author AcoYT
 */
/*
@Environment(EnvType.CLIENT)
public class LoadShadersEvent implements LevelRenderEvents.EndMain {
    private final String packName;
    private final ResourceKey<Level> levelKey;

    public LoadShadersEvent(String packName, ResourceKey<Level> levelKey) {
        this.packName = packName;
        this.levelKey = levelKey;
    }

    @SuppressWarnings("ALL")
    public void endMain(LevelRenderContext context) {
        Level level = ((LevelRendererAccessor) context.levelRenderer()).acornlib$getLevel();
        if (level.dimension().equals(this.levelKey)) {
            if (!Iris.getIrisConfig().areShadersEnabled() || !Iris.getCurrentPackName().equals(this.packName)) {
                try {
                    Iris.getIrisConfig().setShaderPackName(this.packName);
                    Iris.getIrisConfig().setShadersEnabled(true);
                    Iris.getIrisConfig().save();
                    Iris.reload();
                } catch (Exception e) {
                    AcornLib.LOGGER.error("Failed to apply shader pack {} for world {}", this.packName, this.levelKey.identifier());
                }
            }
        } else if (Iris.getCurrentPackName().equals(this.packName)) {
            try {
                Iris.getIrisConfig().setShaderPackName("");
                Iris.getIrisConfig().setShadersEnabled(false);
                Iris.getIrisConfig().save();
                Iris.reload();
            } catch (Exception e) {
                AcornLib.LOGGER.error("Failed to disable shader pack {}", this.packName);
            }
        }
    }
}
*/