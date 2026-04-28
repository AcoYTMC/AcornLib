package net.acoyt.acornlib.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.Vec3d;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author AcoYT
 */
public class VelocityCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess acc, CommandManager.RegistrationEnvironment dedicated) {
        dispatcher.register(literal("velocity").requires(source -> source.hasPermissionLevel(2))
                .then(argument("targets", EntityArgumentType.entities())
                        .then(literal("add")
                                .then(argument("vec3", Vec3ArgumentType.vec3(false))
                                        .executes(context -> {
                                            for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {
                                                Vec3d vec3 = Vec3ArgumentType.getVec3(context, "vec3");

                                                if (entity instanceof LivingEntity living) {
                                                    living.addVelocity(vec3.x, vec3.y, vec3.z);
                                                    living.velocityModified = true;
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("set")
                                .then(argument("vec3", Vec3ArgumentType.vec3(false))
                                        .executes(context -> {
                                            for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {
                                                Vec3d vec3 = Vec3ArgumentType.getVec3(context, "vec3");

                                                if (entity instanceof LivingEntity living) {
                                                    living.setVelocity(vec3.x, vec3.y, vec3.z);
                                                    living.velocityModified = true;
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("directional")
                                .then(argument("vec3", Vec3ArgumentType.vec3(false))
                                        .executes(context -> {
                                            for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {
                                                Vec3d vec3 = Vec3ArgumentType.getVec3(context, "vec3");

                                                if (entity instanceof LivingEntity living) {
                                                    living.setVelocity(living.getRotationVector().multiply(vec3));
                                                    living.velocityModified = true;
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("byEntity")
                                .then(argument("anchor", EntityArgumentType.entity())
                                        .then(argument("multiplier", FloatArgumentType.floatArg())
                                                .executes(context -> {
                                                    for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {
                                                        Entity anchor = EntityArgumentType.getEntity(context, "anchor");
                                                        float multiplier = FloatArgumentType.getFloat(context, "multiplier");

                                                        if (entity instanceof LivingEntity target && anchor instanceof LivingEntity byEntity) {
                                                            target.setVelocity(byEntity.getPos().subtract(target.getPos()).multiply(multiplier));
                                                            target.velocityModified = true;
                                                        }
                                                    }

                                                    return Command.SINGLE_SUCCESS;
                                                })
                                        )
                                )
                        ).then(literal("multiply")
                                .then(argument("multiplier", FloatArgumentType.floatArg())
                                        .executes(context -> {
                                            for (Entity entity : EntityArgumentType.getEntities(context, "targets")) {
                                                float multiplier = FloatArgumentType.getFloat(context, "multiplier");

                                                if (entity instanceof LivingEntity living) {
                                                    living.setVelocity(living.getVelocity().multiply(multiplier));
                                                    living.velocityModified = true;
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
