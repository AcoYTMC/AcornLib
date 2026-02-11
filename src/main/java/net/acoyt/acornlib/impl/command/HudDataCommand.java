package net.acoyt.acornlib.impl.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.acoyt.acornlib.impl.cca.entity.AcornData;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class HudDataCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess acc, CommandManager.RegistrationEnvironment dedicated) {
        dispatcher.register(literal("hud_data")
                .then(argument("players", EntityArgumentType.players())
                        .then(literal("all")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                data.setAll(value);
                                                context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "all", String.valueOf(value)), true);
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("events")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.events != value) {
                                                    data.events = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "events", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("overlays")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.overlays != value) {
                                                    data.overlays = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "overlays", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("crosshair")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.crosshair != value) {
                                                    data.crosshair = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "crosshair", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("hotbar")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.hotbar != value) {
                                                    data.hotbar = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "hotbar", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("armor")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.armor != value) {
                                                    data.armor = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "armor", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("health")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.health != value) {
                                                    data.health = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "health", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("hunger")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.hunger != value) {
                                                    data.hunger = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "hunger", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("bubbles")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.bubbles != value) {
                                                    data.bubbles = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "bubbles", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("experience")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.experience != value) {
                                                    data.experience = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "experience", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("effects")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.effects != value) {
                                                    data.effects = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "effects", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("bossbar")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.bossbar != value) {
                                                    data.bossbar = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "bossbar", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("debugHud")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.debugHud != value) {
                                                    data.debugHud = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "debugHud", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("sidebar")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.sidebar != value) {
                                                    data.sidebar = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "sidebar", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("titles")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.titles != value) {
                                                    data.titles = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "titles", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("chat")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.chat != value) {
                                                    data.chat = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "chat", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("players")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.players != value) {
                                                    data.players = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "players", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("subtitles")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.subtitles != value) {
                                                    data.subtitles = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "subtitles", String.valueOf(value)), true);
                                                }
                                            }

                                            return Command.SINGLE_SUCCESS;
                                        })
                                )
                        ).then(literal("tooltip")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "players")) {
                                                AcornData data = KEY.get(player);
                                                boolean value = BoolArgumentType.getBool(context, "value");

                                                if (data.tooltip != value) {
                                                    data.tooltip = value;
                                                    data.sync();
                                                    context.getSource().sendFeedback(() -> Text.translatable("command.acornlib.hud_data.set", "tooltip", String.valueOf(value)), true);
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
