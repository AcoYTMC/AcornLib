package net.acoyt.acornlib.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.acoyt.acornlib.api.util.VelocityUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class VelocityCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("velocity").requires(source -> source.hasPermission(2))
                .then(argument("targets", EntityArgument.entities())
                        .then(literal("exact")
                                .then(argument("x", FloatArgumentType.floatArg(-999.9f, 999.9f))
                                        .then(argument("y", FloatArgumentType.floatArg(-999.9f, 999.9f))
                                                .then(argument("z", FloatArgumentType.floatArg(-999.9f, 999.9f))
                                                        .executes((context) -> {
                                                            for (Entity entity : EntityArgument.getEntities(context, "targets")) {

                                                                float x = FloatArgumentType.getFloat(context, "x");
                                                                float y = FloatArgumentType.getFloat(context, "y");
                                                                float z = FloatArgumentType.getFloat(context, "z");

                                                                if (entity instanceof LivingEntity living) {
                                                                    VelocityUtils.applyExactVelocity(living, x, y, z, false);
                                                                }
                                                            }

                                                            return Command.SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                )
                        ).then(literal("directional")
                                .then(argument("x", FloatArgumentType.floatArg(-999.9f, 999.9f))
                                        .then(argument("y", FloatArgumentType.floatArg(-999.9f, 999.9f))
                                                .then(argument("z", FloatArgumentType.floatArg(-999.9f, 999.9f))
                                                        .executes((context) -> {
                                                            for (Entity entity : EntityArgument.getEntities(context, "targets")) {

                                                                float x = FloatArgumentType.getFloat(context, "x");
                                                                float y = FloatArgumentType.getFloat(context, "y");
                                                                float z = FloatArgumentType.getFloat(context, "z");

                                                                if (entity instanceof LivingEntity living) {
                                                                    VelocityUtils.applyVelocityInLookDirection(living, x, y, z, false);
                                                                }
                                                            }

                                                            return Command.SINGLE_SUCCESS;
                                                        })
                                                )
                                        )
                                )
                        ).then(literal("byEntity")
                                .then(argument("anchor", EntityArgument.entity())
                                        .then(argument("multiplier", FloatArgumentType.floatArg(0.1F))
                                                .executes(context -> {
                                                    for (Entity target : EntityArgument.getEntities(context, "targets")) {
                                                        Entity entity = EntityArgument.getEntity(context, "anchor");
                                                        float multiplier = FloatArgumentType.getFloat(context, "multiplier");

                                                        if (target instanceof LivingEntity livingTarget && entity instanceof LivingEntity livingEntity) {
                                                            VelocityUtils.applyVelocityByEntity(livingTarget, livingEntity, multiplier, false);
                                                        }
                                                    }

                                                    return Command.SINGLE_SUCCESS;
                                                })
                                        )
                                )
                        )
                )
        );
    }
}
