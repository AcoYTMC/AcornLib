package net.acoyt.acornlib.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class AcornLibCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("acornlib")
                .then(literal("isSupporter")
                        .then(argument("player", EntityArgument.player())
                                .executes(context -> {
                                    if (context.getSource().getPlayer() != null) {
                                        Player player = EntityArgument.getPlayer(context, "player");
                                        context.getSource().getPlayer().sendSystemMessage(
                                                Component.literal("Player " +
                                                        player.getDisplayName().getString()
                                                        + (AcornLib.isSupporter(player) ? " is a supporter!" : " is not a supporter!")
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
