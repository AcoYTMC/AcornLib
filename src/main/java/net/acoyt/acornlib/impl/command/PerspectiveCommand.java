package net.acoyt.acornlib.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.acoyt.acornlib.impl.networking.clientbound.ForcePerspectivePayload;
import net.acoyt.acornlib.impl.util.AcornUtil;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * @author AcoYT
 */
public class PerspectiveCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext ignoredContext, Commands.CommandSelection ignoredSelection) {
        dispatcher.register(literal("perspective").requires(AcornUtil::hasPermissions)
                .then(argument("player", EntityArgument.player())
                        .then(literal("force")
                                .then(argument("perspective", StringArgumentType.string()).suggests(PerspectiveCommand::getSuggestions)
                                        .executes(context -> {
                                            String perspective = StringArgumentType.getString(context, "perspective").toUpperCase(Locale.ROOT);
                                            ServerPlayer player = EntityArgument.getPlayer(context, "player");
                                            if (!KEY.get(player).getStoredPerspective().equalsIgnoreCase(perspective)) {
                                                ServerPlayNetworking.send(player, new ForcePerspectivePayload(perspective));
                                                context.getSource().sendSuccess(() -> Component.translatable("command.acornlib.perspective.set", perspective, getName(player)), true);
                                            } else {
                                                context.getSource().sendSuccess(() -> Component.translatable("command.acornlib.perspective.already_value", perspective, getName(player)), true);
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("get")
                                .executes(context -> {
                                    ServerPlayer player = EntityArgument.getPlayer(context, "player");
                                    String perspective = KEY.get(player).getStoredPerspective();
                                    if (perspective == null || perspective.isEmpty()) {
                                        context.getSource().sendSuccess(() -> Component.translatable("command.acornlib.perspective.empty", getName(player)), true);
                                    } else {
                                        context.getSource().sendSuccess(() -> Component.translatable("command.acornlib.perspective.get", perspective, getName(player)), true);
                                    }

                                    return Command.SINGLE_SUCCESS;
                                })
                        )
                )
        );
    }

    private static CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) {
        return builder
                .suggest("FIRST_PERSON")
                .suggest("THIRD_PERSON_FRONT")
                .suggest("THIRD_PERSON_BACK")
                .buildFuture();
    }

    private static String getName(ServerPlayer player) {
        //? if > 1.21.10 {
        /*return player.getGameProfile().name();
        *///? } else {
        return player.getGameProfile().getName();
        //? }
    }
}
