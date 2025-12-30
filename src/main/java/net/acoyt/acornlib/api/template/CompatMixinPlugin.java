package net.acoyt.acornlib.api.template;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
public abstract class CompatMixinPlugin implements IMixinConfigPlugin {
    private final String packageRoot;

    public CompatMixinPlugin(String packageRoot) {
        this.packageRoot = packageRoot;
    }

    @Override
    public void onLoad(String mixinPackage) {
        //
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.startsWith("compat.")) {
            return FabricLoader.getInstance().isModLoaded(formatCompat(mixinClassName));
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        //
    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        //
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        //
    }

    public static String formatCompat(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        text = text.replace("compat.", "");

        StringBuilder builder = new StringBuilder();

        boolean ignoreRest = false;
        for (char ch : text.toCharArray()) {
            if (ch == '.') {
                ignoreRest = true;
            } else if (!ignoreRest) {
                builder.append(ch);
            }
        }

        return builder.toString();
    }
}
