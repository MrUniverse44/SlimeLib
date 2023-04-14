package me.blueslime.slimelib.logs.platforms.spigot;

import me.blueslime.slimelib.logs.SlimeLogger;
import me.blueslime.slimelib.logs.SlimeLogs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class SlimeLoggerSpigot extends SlimeLogs {

    private final Server server;

    public SlimeLoggerSpigot(JavaPlugin plugin) {
        super();
        this.server = plugin.getServer();
    }

    public SlimeLoggerSpigot(JavaPlugin plugin, SlimeLogger logger) {
        super(logger);
        this.server = plugin.getServer();
    }

    public SlimeLoggerSpigot(SlimeLogger logger) {
        super(logger);
        this.server = Bukkit.getServer();
    }

    public SlimeLoggerSpigot() {
        super();
        this.server = Bukkit.getServer();
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public void send(String message) {
        server.getConsoleSender().sendMessage(
                color(message)
        );
    }
}
