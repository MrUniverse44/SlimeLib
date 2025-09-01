package me.blueslime.slimelib.commands;

import me.blueslime.slimelib.SlimePlatform;
import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.commands.bungee.BungeeCommand;
import me.blueslime.slimelib.commands.command.SlimeCommand;
import me.blueslime.slimelib.commands.bukkit.BukkitCommand;
import me.blueslime.slimelib.commands.velocity.VelocityCommand;

public interface SlimeCommandPlatform {

    void register(SlimeCommand command);

    void unregister();

    static <T> SlimeCommandPlatform fromMode(SlimePlugin<T> plugin, SlimePlatform mode) {
        switch (mode) {
            default:
            case BUNGEECORD:
                return new BungeeCommand(plugin).get();
            case BUKKIT:
            case PAPER:
            case SPIGOT:
                return new BukkitCommand(plugin).get();
            case VELOCITY:
                return new VelocityCommand(plugin).get();
        }
    }
}
