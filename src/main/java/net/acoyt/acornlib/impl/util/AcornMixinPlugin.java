package net.acoyt.acornlib.impl.util;

import com.mojang.logging.LogUtils;
import net.acoyt.acornlib.api.annotation.DevelopmentOnly;
import net.acoyt.acornlib.api.annotation.RequiresMod;
import net.acoyt.acornlib.api.template.CompatMixinPlugin;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.spongepowered.asm.service.MixinService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.acoyt.acornlib.api.util.MiscUtils.ifDev;

/**
 * @author AcoYT
 */
public class AcornMixinPlugin extends CompatMixinPlugin {
    private static final Logger LOGGER = LogUtils.getLogger();

    private List<String> loadedMods = new ArrayList<>();

    public AcornMixinPlugin() {
        super("net.acoyt.acornlib.mixin.");
    }

    public void onLoad(String mixinPackage) {
        this.loadedMods = FabricLoader.getInstance().getAllMods().stream()
                .map(container -> container.getMetadata().getId())
                .toList();
    }

    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        try {
            ClassNode clazz = MixinService.getService().getBytecodeProvider().getClassNode(mixinClassName);
            return super.shouldApplyMixin(targetClassName, mixinClassName) && testForAnnotations(clazz, mixinClassName);
        } catch (ClassNotFoundException | IOException e) {
            return super.shouldApplyMixin(targetClassName, mixinClassName);
        }
    }

    private boolean testForAnnotations(ClassNode mixinClass, String mixinClassName) {
        boolean annotatedDev = false;
        if (mixinClass.visibleAnnotations != null) {
            for (AnnotationNode node : mixinClass.visibleAnnotations) {
                if (node.desc.equals(Type.getDescriptor(DevelopmentOnly.class))) {
                    annotatedDev = true;
                    continue;
                }

                if (node.desc.equals(Type.getDescriptor(RequiresMod.class))) {
                    if (!isModPresent(node, mixinClassName)) return false;
                }
            }
        }

        return !annotatedDev || FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    private boolean isModPresent(AnnotationNode node, String mixinClassName) {
        String value = getNodeValue(node, "value", "");
        if (value.isEmpty()) throw new IllegalArgumentException("Checked modId must not be blank");
        if (!loadedMods.contains(value)) {
            ifDev(() -> LOGGER.debug("Skipping mixin {}. Mod {} is not loaded", mixinClassName, value));
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getNodeValue(AnnotationNode node, String key, T fallback) {
        if (node.values == null) return fallback;

        for (int i = 0; i < node.values.size(); i++) {
            if (key.equals(node.values.get(i))) {
                return (T) node.values.get(i + 1);
            }
        }

        return fallback;
    }
}
