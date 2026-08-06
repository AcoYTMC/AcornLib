package net.acoyt.acornlib.impl.util.data;

import com.mojang.serialization.JsonOps;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.resources.Identifier;
import java.util.HashMap;
import java.util.Map;

//? if > 1.21.11 {
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.SimpleReloadListener;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
//? } else {
/*import com.mojang.serialization.DataResult;
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.11 {
public class ModMenuDataReloadListener extends SimpleReloadListener<Map<String, ALib.ModMenuData>> {
 //? } else {
/*public class ModMenuDataReloadListener implements SimpleSynchronousResourceReloadListener {
    *///? }
    public static final String PATH = "modmenu_data";
    public static final Map<String, ALib.ModMenuData> DATA_MAP = new HashMap<>();

    //? if > 1.21.11 {
    public Map<String, ALib.ModMenuData> prepare(SharedState state) {
        Map<Identifier, ALib.ModMenuData> raw = new HashMap<>();
        SimpleJsonResourceReloadListener.scanDirectory(state.resourceManager(), FileToIdConverter.json(PATH),
                state.get(ResourceLoader.REGISTRY_LOOKUP_KEY).createSerializationContext(JsonOps.INSTANCE),
                ALib.ModMenuData.CODEC, raw
        );

        Map<String, ALib.ModMenuData> modified = new HashMap<>();
        raw.forEach((id, data) -> modified.put(MiscUtils.formatAfter(id.getPath(), '/').replace(".json", ""), data));
        return modified;
    }

    public void apply(Map<String, ALib.ModMenuData> prepared, SharedState state) {
        DATA_MAP.clear();
        DATA_MAP.putAll(prepared);
    }
    //? } else {
    /*public Identifier getFabricId() {
        return AcornLib.id(PATH);
    }

    public void onResourceManagerReload(ResourceManager manager) {
        DATA_MAP.clear();

        manager.listResources(PATH, path -> path.getPath().endsWith(".json")).keySet().forEach(id -> {
            if (manager.getResource(id).isPresent()) {
                try (InputStream stream = manager.getResource(id).get().open()) {
                    var json = GsonHelper.parse(new InputStreamReader(stream, StandardCharsets.UTF_8));
                    DataResult<ALib.ModMenuData> result = ALib.ModMenuData.CODEC.parse(JsonOps.INSTANCE, json);

                    result.resultOrPartial(AcornLib.LOGGER::error).ifPresent(data -> DATA_MAP.put(MiscUtils.formatAfter(id.getPath(), '/').replace(".json", ""), data));
                } catch (Exception e) {
                    AcornLib.LOGGER.info("Failed to load ModMenu Data file {}: {}", id, e.getMessage());
                }
            }
        });
    }
    *///? }

    public static Map<String, ALib.ModMenuData> getEntries() {
        Map<String, ALib.ModMenuData> values = new HashMap<>(ALib.MM_DATA);
        DATA_MAP.forEach((id, data) -> values.computeIfAbsent(id, st -> data));
        return values;
    }
}
