package me.blueslime.slimelib.logs.platforms.bungee;

import me.blueslime.slimelib.logs.SlimeLogger;
import me.blueslime.slimelib.logs.SlimeLogs;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

public class SlimeLoggerBungee extends SlimeLogs {

    private final CommandSender server;

    public SlimeLoggerBungee(Plugin plugin) {
        super();
        this.server = plugin.getProxy().getConsole();
    }

    public SlimeLoggerBungee(Plugin plugin, SlimeLogger logger) {
        super(logger);
        this.server = plugin.getProxy().getConsole();
    }

    public SlimeLoggerBungee() {
        super();
        this.server = ProxyServer.getInstance().getConsole();
    }

    public SlimeLoggerBungee(SlimeLogger logger) {
        super(logger);
        this.server = ProxyServer.getInstance().getConsole();
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public void send(String message) {
        server.sendMessage(
                new TextComponent(
                        color(message)
                )
        );
    }
}
