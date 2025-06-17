package net.acoyt.acornlib.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class VelocityCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("velocity").requires(source -> source.hasPermissionLevel(2))
                .then(literal("exact")
                        .then(argument("x", FloatArgumentType.floatArg(0.0f, 999.9f))
                                .then(argument("y", FloatArgumentType.floatArg(0.0f, 999.9f))
                                        .then(argument("z", FloatArgumentType.floatArg(0.0f, 999.9f))
                                                .then(literal("inverted")
                                                        .executes((context) -> {
                                                            ServerCommandSource source = (ServerCommandSource)context.getSource();
                                                            Entity player = source.getEntity();

                                                            player.velocityModified = true;
                                                            player.setVelocity(
                                                                    FloatArgumentType.getFloat(context, "x") * -1,
                                                                    FloatArgumentType.getFloat(context, "y") * -1,
                                                                    FloatArgumentType.getFloat(context, "z") * -1
                                                            );
                                                            return 1;
                                                        })

                                                ).executes((context) -> {
                                                    ServerCommandSource source = (ServerCommandSource)context.getSource();
                                                    Entity player = source.getEntity();

                                                    player.velocityModified = true;
                                                    player.setVelocity(
                                                            FloatArgumentType.getFloat(context, "x"),
                                                            FloatArgumentType.getFloat(context, "y"),
                                                            FloatArgumentType.getFloat(context, "z")
                                                    );
                                                    return 1;
                                                })
                                        )
                                )
                        )
                ).then(literal("directional")
                        .then(argument("x", FloatArgumentType.floatArg(0.0f, 999.9f))
                                .then(argument("y", FloatArgumentType.floatArg(0.0f, 999.9f))
                                        .then(argument("z", FloatArgumentType.floatArg(0.0f, 999.9f))
                                                .then(literal("inverted")
                                                        .executes((context) -> {
                                                            ServerCommandSource source = (ServerCommandSource)context.getSource();
                                                            Entity player = source.getEntity();

                                                            player.velocityModified = true;
                                                            player.setVelocity(
                                                                    player.getRotationVector().x * FloatArgumentType.getFloat(context, "x") * -1,
                                                                    player.getRotationVector().y * FloatArgumentType.getFloat(context, "y") * -1,
                                                                    player.getRotationVector().z * FloatArgumentType.getFloat(context, "z") * -1
                                                            );
                                                            return 1;
                                                        })

                                                ).executes((context) -> {
                                                    ServerCommandSource source = (ServerCommandSource)context.getSource();
                                                    Entity player = source.getEntity();

                                                    player.velocityModified = true;
                                                    player.setVelocity(
                                                            player.getRotationVector().x * FloatArgumentType.getFloat(context, "x"),
                                                            player.getRotationVector().y * FloatArgumentType.getFloat(context, "y"),
                                                            player.getRotationVector().z * FloatArgumentType.getFloat(context, "z")
                                                    );
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
        );
    }
}
