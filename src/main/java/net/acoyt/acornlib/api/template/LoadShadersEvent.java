package net.acoyt.acornlib.api.template;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.irisshaders.iris.Iris;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class LoadShadersEvent implements WorldRenderEvents.End {
    private final String packName;
    private final RegistryKey<World> worldKey;

    public LoadShadersEvent(String packName, RegistryKey<World> worldKey) {
        this.packName = packName;
        this.worldKey = worldKey;
    }

    public void onEnd(WorldRenderContext context) {
        if (context.world().getRegistryKey().equals(this.worldKey)) {
            if (!Iris.getIrisConfig().areShadersEnabled() || !Iris.getCurrentPackName().equals(this.packName)) {
                try {
                    Iris.getIrisConfig().setShaderPackName(this.packName);
                    Iris.getIrisConfig().setShadersEnabled(true);
                    Iris.getIrisConfig().save();
                    Iris.reload();
                } catch (Exception e) {
                    //
                }
            }
        } else if (Iris.getCurrentPackName().equals(this.packName)) {
            try {
                Iris.getIrisConfig().setShaderPackName("");
                Iris.getIrisConfig().setShadersEnabled(false);
                Iris.getIrisConfig().save();
                Iris.reload();
            } catch (Exception e) {
                //
            }
        }
    }
}
