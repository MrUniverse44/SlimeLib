package me.blueslime.slimelib.source.console;

import me.blueslime.slimelib.source.SlimeSource;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.UUID;

public class SlimeConsoleBungee implements SlimeSource<CommandSender> {
    private static SlimeConsoleBungee BUNGEE_CONSOLE = null;

    public static SlimeConsoleBungee instance() {
        if (BUNGEE_CONSOLE == null) {
            BUNGEE_CONSOLE = new SlimeConsoleBungee();
        }
        return BUNGEE_CONSOLE;
    }

    private CommandSender sender() {
        return ProxyServer.getInstance().getConsole();
    }

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    @Override
    public boolean hasPermission(String permission) {
        return sender().hasPermission(permission);
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isConsoleSender() {
        return true;
    }

    @Override
    public CommandSender getOriginalSource() {
        return sender();
    }

    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public UUID getUniqueId() {
        return UUID.randomUUID();
    }

    @Override
    public String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public void sendMessage(String message) {
        sender().sendMessage(
                new TextComponent(
                        message
                )
        );
    }

    @Override
    public void sendMessage(String[] message) {
        for(String text : message) {
            sender().sendMessage(
                    new TextComponent(
                            text
                    )
            );
        }
    }

    @Override
    public void sendColoredMessage(String message) {
        sender().sendMessage(
                new TextComponent(
                        color(message)
                )
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for(String text : message) {
            sender().sendMessage(
                    new TextComponent(
                            color(text)
                    )
            );
        }
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public CommandSender get() {
        return sender();
    }
}


