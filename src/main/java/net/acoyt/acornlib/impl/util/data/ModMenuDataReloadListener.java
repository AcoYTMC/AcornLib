package net.acoyt.acornlib.impl.util.data;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashMap;
import java.util.Map;

//? if > 1.21.1 {
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
//? } else {
/*import com.mojang.serialization.JsonOps;
import com.mojang.serialization.DataResult;
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.util.GsonHelper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.1 {
public class ModMenuDataReloadListener extends SimpleJsonResourceReloadListener<ALib.ModMenuData> {
 //? } else {
/*public class ModMenuDataReloadListener implements SimpleSynchronousResourceReloadListener {
    *///? }
    public static final String PATH = "modmenu_data";
    public static final Map<Identifier, ALib.ModMenuData> DATA_MAP = new HashMap<>();

    //? if > 1.21.1 {
    public ModMenuDataReloadListener() {
        super(ALib.ModMenuData.CODEC, FileToIdConverter.json(PATH));
    }

    public void apply(Map<Identifier, ALib.ModMenuData> preparations, ResourceManager manager, ProfilerFiller profiler) {
        DATA_MAP.clear();
        DATA_MAP.putAll(preparations);
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

                    result.resultOrPartial(AcornLib.LOGGER::error).ifPresent(data -> DATA_MAP.put(id, data));
                } catch (Exception e) {
                    AcornLib.LOGGER.info("Failed to load ModMenu Data file {}: {}", id, e.getMessage());
                }
            }
        });
    }
    *///? }

    public static Map<String, ALib.ModMenuData> getEntries() {
        Map<String, ALib.ModMenuData> values = new HashMap<>(ALib.MM_DATA);
        DATA_MAP.forEach((id, data) -> {
            String formatted = MiscUtils.formatAfter(id.getPath(), '/').replace(".json", "");
            values.computeIfAbsent(formatted, st -> data);
        });

        return values;
    }
}
