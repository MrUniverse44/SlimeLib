package dev.mruniverse.slimelib.logs.platforms.spigot;

import dev.mruniverse.slimelib.logs.SlimeLogger;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class SlimeLoggerSpigot extends SlimeLogs {

    private final JavaPlugin plugin;

    public SlimeLoggerSpigot(JavaPlugin plugin) {
        super();
        this.plugin = plugin;
    }

    public SlimeLoggerSpigot(JavaPlugin plugin, SlimeLogger logger) {
        super(logger);
        this.plugin = plugin;
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public void send(String message) {
        plugin.getServer().getConsoleSender().sendMessage(
                color(message)
        );
    }
}
