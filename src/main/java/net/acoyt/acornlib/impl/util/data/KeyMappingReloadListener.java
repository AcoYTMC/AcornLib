/*package net.acoyt.acornlib.impl.util.data;

import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.reloader.SimpleReloadListener;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;

import java.util.HashMap;
import java.util.Map;
 */

/**
 * @author AcoYT
 */
/*public class KeyMappingReloadListener extends SimpleReloadListener<Map<Identifier, KeyMapping>> {
    public static final String PATH = "key_mappings";

    public static final Map<Identifier, KeyMapping> KEYMAPPINGS = new HashMap<>();
    public static final Map<KeyMapping, Identifier> BACKWARDS = new HashMap<>();

    public Map<Identifier, KeyMapping> prepare(SharedState state) {
        Map<Identifier, KeyData> raw = new HashMap<>();
        SimpleJsonResourceReloadListener.scanDirectory(state.resourceManager(), FileToIdConverter.json(PATH),
                state.get(ResourceLoader.REGISTRY_LOOKUP_KEY).createSerializationContext(JsonOps.INSTANCE),
                KeyData.CODEC, raw
        );

        Map<Identifier, KeyMapping> finalized = new HashMap<>();
        raw.forEach((id, data) -> finalized.put(id, data.asKeyMapping()));
        return finalized;
    }

    public void apply(Map<Identifier, KeyMapping> prepared, SharedState state) {
        KEYMAPPINGS.clear();
        KEYMAPPINGS.putAll(prepared);

        BACKWARDS.clear();
        KEYMAPPINGS.forEach((id, mapping) -> BACKWARDS.put(mapping, id));
    }
}
 */