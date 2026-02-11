package net.acoyt.acornlib.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.acoyt.acornlib.impl.networking.ForcePerspectivePayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class PerspectiveCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess acc, CommandManager.RegistrationEnvironment dedicated) {
        dispatcher.register(literal("perspective").requires(source -> source.hasPermissionLevel(2))
                .then(argument("player", EntityArgumentType.player())
                        .then(literal("force")
                                .then(argument("perspective", StringArgumentType.string()).suggests(PerspectiveCommand::getSuggestions)
                                        .executes(context -> {
                                            String perspective = StringArgumentType.getString(context, "perspective").toUpperCase(Locale.ROOT);
                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                            if (!KEY.get(player).getStoredPerspective().equalsIgnoreCase(perspective)) {
                                                ServerPlayNetworking.send(player, new ForcePerspectivePayload(perspective));
                                                context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.perspective.set", perspective, player.getGameProfile().getName()), true);
                                            } else {
                                                context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.perspective.already_value", perspective, player.getGameProfile().getName()), true);
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("get")
                                .executes(context -> {
                                    ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                    String perspective = KEY.get(player).getStoredPerspective();
                                    if (perspective == null || perspective.isEmpty()) {
                                        context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.perspective.empty", player.getGameProfile().getName()), true);
                                    } else {
                                        context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.perspective.get", perspective, player.getGameProfile().getName()), true);
                                    }

                                    return Command.SINGLE_SUCCESS;
                                })
                        )
                )
        );
    }

    private static CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
        return builder
                .suggest("FIRST_PERSON")
                .suggest("THIRD_PERSON_FRONT")
                .suggest("THIRD_PERSON_BACK")
                .buildFuture();
    }
}
