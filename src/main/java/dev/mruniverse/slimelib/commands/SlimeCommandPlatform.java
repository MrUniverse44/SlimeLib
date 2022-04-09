package dev.mruniverse.slimelib.commands;

import dev.mruniverse.slimelib.PluginMode;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.commands.bungee.BungeeCommand;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.spigot.SpigotCommand;
import dev.mruniverse.slimelib.commands.velocity.VelocityCommand;

public interface SlimeCommandPlatform {

    void register(SlimeCommand command);

    void unregister();

    static <T> SlimeCommandPlatform fromMode(SlimePlugin<T> plugin, PluginMode mode) {
        switch (mode) {
            default:
            case BUNGEECORD:
                return new BungeeCommand(plugin).get();
            case SPIGOT:
                return new SpigotCommand(plugin).get();
            case VELOCITY:
                return new VelocityCommand(plugin).get();
        }
    }
}
