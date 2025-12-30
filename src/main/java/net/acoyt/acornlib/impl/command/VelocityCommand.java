package net.acoyt.acornlib.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.acoyt.acornlib.api.util.VelocityUtils;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class VelocityCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("velocity").requires(source -> source.hasPermissionLevel(2))
                .then(argument("targets", EntityArgumentType.entities())
                        .then(literal("exact")
                                .then(argument("x", FloatArgumentType.floatArg(0.0f, 999.9f))
                                        .then(argument("y", FloatArgumentType.floatArg(0.0f, 999.9f))
                                                .then(argument("z", FloatArgumentType.floatArg(0.0f, 999.9f))
                                                        .executes((context) -> {
                                                            for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {

                                                                float x = FloatArgumentType.getFloat(context, "x");
                                                                float y = FloatArgumentType.getFloat(context, "y");
                                                                float z = FloatArgumentType.getFloat(context, "z");

                                                                if (entity instanceof LivingEntity living) {
                                                                    VelocityUtils.applyExactVelocity(living, x, y, z, false);
                                                                }
                                                            }

                                                            return Command.SINGLE_SUCCESS;
                                                        }).then(argument("inverted", BoolArgumentType.bool())
                                                                .executes((context) -> {
                                                                    for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {
                                                                        boolean inverted = BoolArgumentType.getBool(context, "inverted");
                                                                        float x = FloatArgumentType.getFloat(context, "x");
                                                                        float y = FloatArgumentType.getFloat(context, "y");
                                                                        float z = FloatArgumentType.getFloat(context, "z");

                                                                        if (entity instanceof LivingEntity living) {
                                                                            VelocityUtils.applyExactVelocity(living, x, y, z, inverted);
                                                                        }
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
                                                        .executes((context) -> {
                                                            for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {

                                                                float x = FloatArgumentType.getFloat(context, "x");
                                                                float y = FloatArgumentType.getFloat(context, "y");
                                                                float z = FloatArgumentType.getFloat(context, "z");

                                                                if (entity instanceof LivingEntity living) {
                                                                    VelocityUtils.applyVelocityInLookDirection(living, x, y, z, false);
                                                                }
                                                            }

                                                            return Command.SINGLE_SUCCESS;
                                                        }).then(argument("inverted", BoolArgumentType.bool())
                                                                .executes((context) -> {
                                                                    for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {
                                                                        boolean inverted = BoolArgumentType.getBool(context, "inverted");
                                                                        float x = FloatArgumentType.getFloat(context, "x");
                                                                        float y = FloatArgumentType.getFloat(context, "y");
                                                                        float z = FloatArgumentType.getFloat(context, "z");

                                                                        if (entity instanceof LivingEntity living) {
                                                                            VelocityUtils.applyVelocityInLookDirection(living, x, y, z, inverted);
                                                                        }
                                                                    }

                                                                    return Command.SINGLE_SUCCESS;
                                                                })
                                                        )
                                                )
                                        )
                                )
                        ).then(literal("byEntity")
                                .then(argument("anchor", EntityArgumentType.entity())
                                        .then(argument("multiplier", FloatArgumentType.floatArg(0.1F))
                                                .executes(context -> {
                                                    for (Entity target : EntityArgumentType.getEntities(context, "targets")) {
                                                        Entity entity = EntityArgumentType.getEntity(context, "anchor");
                                                        float multiplier = FloatArgumentType.getFloat(context, "multiplier");

                                                        if (target instanceof LivingEntity livingTarget && entity instanceof LivingEntity livingEntity) {
                                                            VelocityUtils.applyVelocityByEntity(livingTarget, livingEntity, multiplier, false);
                                                        }
                                                    }

                                                    return Command.SINGLE_SUCCESS;
                                                }).then(argument("inverted", BoolArgumentType.bool())
                                                        .executes(context -> {
                                                            for (Entity target : EntityArgumentType.getEntities(context, "targets")) {
                                                                Entity entity = EntityArgumentType.getEntity(context, "anchor");
                                                                float multiplier = FloatArgumentType.getFloat(context, "multiplier");
                                                                boolean inverted = BoolArgumentType.getBool(context, "inverted");

                                                                if (target instanceof LivingEntity livingTarget && entity instanceof LivingEntity livingEntity) {
                                                                    VelocityUtils.applyVelocityByEntity(livingTarget, livingEntity, multiplier, inverted);
                                                                }
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
