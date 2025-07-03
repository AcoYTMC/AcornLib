package net.acoyt.acornlib.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.acoyt.acornlib.util.supporter.SupporterUtils;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class AcornLibCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("acornlib")
                .then(literal("isSupporter")
                        .then(argument("player", EntityArgumentType.player())
                                .executes(context -> {
                                    if (context.getSource().getPlayer() != null) {
                                        PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                        context.getSource().getPlayer().sendMessage(
                                                Text.literal("Player " +
                                                        player.getDisplayName().getString()
                                                        + (SupporterUtils.isPlayerSupporter(player) || SupporterUtils.isPlayerFriend(player) ? " is a supporter!" : " is not a supporter!")
                                                )
                                        );
                                    }
                                    return Command.SINGLE_SUCCESS;
                                })
                        )
                )
        );
    }
}
