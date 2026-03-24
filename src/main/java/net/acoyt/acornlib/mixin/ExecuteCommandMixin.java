package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ExecuteCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@Mixin(ExecuteCommand.class)
public abstract class ExecuteCommandMixin {
    @Shadow
    private static ArgumentBuilder<ServerCommandSource, ?> addConditionLogic(CommandNode<ServerCommandSource> root, ArgumentBuilder<ServerCommandSource, ?> builder, boolean positive, ExecuteCommand.Condition condition) {
        throw new AssertionError();
    }

    @Unique
    private static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @ModifyReturnValue(method = "addConditionArguments", at = @At("RETURN"))
    private static ArgumentBuilder<ServerCommandSource, ?> acornlib$addArguments(
            ArgumentBuilder<ServerCommandSource, ?> original,
            CommandNode<ServerCommandSource> root,
            LiteralArgumentBuilder<ServerCommandSource> argumentBuilder,
            boolean positive,
            CommandRegistryAccess commandRegistryAccess
    ) {
        original.then(literal("mod")
                .then(
                        addConditionLogic(
                                root,
                                argument("modId", StringArgumentType.string()),
                                positive,
                                context -> isModLoaded(StringArgumentType.getString(context, "modId"))
                        )
                )
        );

        return original;
    }
}
