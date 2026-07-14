package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.ExecuteCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * @author AcoYT
 */
@Mixin(ExecuteCommand.class)
public abstract class ExecuteCommandMixin {
    @Shadow
    private static ArgumentBuilder<CommandSourceStack, ?> addConditional(CommandNode<CommandSourceStack> root, ArgumentBuilder<CommandSourceStack, ?> builder, boolean positive, ExecuteCommand.CommandPredicate condition) {
        throw new AssertionError();
    }

    @Unique
    private static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @ModifyReturnValue(method = "addConditionals", at = @At("RETURN"))
    private static ArgumentBuilder<CommandSourceStack, ?> acornlib$addArguments(
            ArgumentBuilder<CommandSourceStack, ?> original,
            CommandNode<CommandSourceStack> execute,
            LiteralArgumentBuilder<CommandSourceStack> parent,
            boolean expected,
            CommandBuildContext context
    ) {
        original.then(literal("mod")
                .then(
                        addConditional(
                                execute,
                                argument("modId", StringArgumentType.string()),
                                expected,
                                commandContext -> isModLoaded(StringArgumentType.getString(commandContext, "modId"))
                        )
                )
        );

        return original;
    }
}
