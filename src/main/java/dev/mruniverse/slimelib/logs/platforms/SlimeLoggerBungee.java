package dev.mruniverse.slimelib.logs.platforms;

import dev.mruniverse.slimelib.logs.SlimeLogger;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public class SlimeLoggerBungee extends SlimeLogs {

    private final Plugin plugin;

    public SlimeLoggerBungee(Plugin plugin) {
        super();
        this.plugin = plugin;
    }

    public SlimeLoggerBungee(Plugin plugin, SlimeLogger logger) {
        super(logger);
        this.plugin = plugin;
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public void send(String message) {
        plugin.getProxy().getConsole().sendMessage(
                new TextComponent(
                        color(message)
                )
        );
    }
}
