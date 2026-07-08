package net.acoyt.acornlib.impl.command;

//~ if > 1.21.11 'hurtMarked' -> 'needsSync' {
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.acoyt.acornlib.impl.util.AcornUtil;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

/**
 * @author AcoYT
 */
public class VelocityCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext ignoredContext, Commands.CommandSelection ignoredSelection) {
        dispatcher.register(literal("velocity").requires(AcornUtil::hasPermissions)
                .then(argument("targets", EntityArgument.entities())
                        .then(literal("add")
                                .then(argument("vec3", Vec3Argument.vec3(false))
                                        .executes(context -> {
                                            for (Entity entity : EntityArgument.getEntities(context, "targets")) {
                                                Vec3 vec3 = Vec3Argument.getVec3(context, "vec3");

                                                if (entity instanceof LivingEntity living) {
                                                    living.push(vec3.x, vec3.y, vec3.z);
                                                    living.hurtMarked = true;
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("set")
                                .then(argument("vec3", Vec3Argument.vec3(false))
                                        .executes(context -> {
                                            for (Entity entity : EntityArgument.getEntities(context, "targets")) {
                                                Vec3 vec3 = Vec3Argument.getVec3(context, "vec3");

                                                if (entity instanceof LivingEntity living) {
                                                    living.setDeltaMovement(vec3.x, vec3.y, vec3.z);
                                                    living.hurtMarked = true;
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("directional")
                                .then(argument("vec3", Vec3Argument.vec3(false))
                                        .executes(context -> {
                                            for (Entity entity : EntityArgument.getEntities(context, "targets")) {
                                                Vec3 vec3 = Vec3Argument.getVec3(context, "vec3");

                                                if (entity instanceof LivingEntity living) {
                                                    living.setDeltaMovement(living.getLookAngle().multiply(vec3));
                                                    living.hurtMarked = true;
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("byEntity")
                                .then(argument("anchor", EntityArgument.entity())
                                        .then(argument("multiplier", FloatArgumentType.floatArg())
                                                .executes(context -> {
                                                    for (Entity entity : EntityArgument.getEntities(context, "targets")) {
                                                        Entity anchor = EntityArgument.getEntity(context, "anchor");
                                                        float multiplier = FloatArgumentType.getFloat(context, "multiplier");

                                                        if (entity instanceof LivingEntity target && anchor instanceof LivingEntity byEntity) {
                                                            target.setDeltaMovement(byEntity.position().subtract(target.position()).scale(multiplier));
                                                            target.hurtMarked = true;
                                                        }
                                                    }

                                                    return Command.SINGLE_SUCCESS;
                                                })
                                        )
                                )
                        ).then(literal("multiply")
                                .then(argument("multiplier", FloatArgumentType.floatArg())
                                        .executes(context -> {
                                            for (Entity entity : EntityArgument.getEntities(context, "targets")) {
                                                float multiplier = FloatArgumentType.getFloat(context, "multiplier");

                                                if (entity instanceof LivingEntity living) {
                                                    living.setDeltaMovement(living.getDeltaMovement().scale(multiplier));
                                                    living.hurtMarked = true;
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        )
                )
        );
    }
}
//~ }