package net.acoyt.acornlib.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.acoyt.acornlib.util.VelocityUtils;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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
                                                .then(argument("inverted", BoolArgumentType.bool())
                                                        .executes((context) -> {
                                                            ServerCommandSource source = context.getSource();
                                                            Entity entity = source.getEntity();

                                                            boolean inverted = BoolArgumentType.getBool(context, "inverted");
                                                            float x = FloatArgumentType.getFloat(context, "x");
                                                            float y = FloatArgumentType.getFloat(context, "y");
                                                            float z = FloatArgumentType.getFloat(context, "z");

                                                            if (entity instanceof LivingEntity living) {
                                                                VelocityUtils.applyExactVelocity(living, x, y, z, inverted);
                                                            }

                                                            return Command.SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                )
                        )
                ).then(literal("directional")
                        .then(argument("x", FloatArgumentType.floatArg(0.0f, 999.9f))
                                .then(argument("y", FloatArgumentType.floatArg(0.0f, 999.9f))
                                        .then(argument("z", FloatArgumentType.floatArg(0.0f, 999.9f))
                                                .then(argument("inverted", BoolArgumentType.bool())
                                                        .executes((context) -> {
                                                            ServerCommandSource source = context.getSource();
                                                            Entity entity = source.getEntity();

                                                            boolean inverted = BoolArgumentType.getBool(context, "inverted");
                                                            float x = FloatArgumentType.getFloat(context, "x");
                                                            float y = FloatArgumentType.getFloat(context, "y");
                                                            float z = FloatArgumentType.getFloat(context, "z");

                                                            if (entity instanceof LivingEntity living) {
                                                                VelocityUtils.applyVelocityInLookDirection(living, x, y, z, inverted);
                                                            }

                                                            return Command.SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                )
                        )
                ).then(literal("byEntity")
                        .then(argument("target", EntityArgumentType.entity())
                                .then(argument("entity", EntityArgumentType.entity())
                                        .then(argument("multiplier", FloatArgumentType.floatArg(0.1F))
                                                .then(argument("inverted", BoolArgumentType.bool())
                                                        .executes(context -> {
                                                            Entity target = EntityArgumentType.getEntity(context, "target");
                                                            Entity entity = EntityArgumentType.getEntity(context, "entity");
                                                            float multiplier = FloatArgumentType.getFloat(context, "multiplier");
                                                            boolean inverted = BoolArgumentType.getBool(context, "inverted");

                                                            if (target instanceof LivingEntity livingTarget && entity instanceof LivingEntity livingEntity) {
                                                                VelocityUtils.applyVelocityByEntity(livingTarget, livingEntity, multiplier, inverted);
                                                            }

                                                            return Command.SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
