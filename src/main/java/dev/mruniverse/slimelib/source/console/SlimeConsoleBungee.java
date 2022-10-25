package dev.mruniverse.slimelib.source.console;

import dev.mruniverse.slimelib.source.SlimeSource;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.UUID;

public class SlimeConsoleBungee implements SlimeSource<CommandSender> {

    private final CommandSender console;

    public SlimeConsoleBungee() {
        this.console = ProxyServer.getInstance().getConsole();
    }

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    @Override
    public boolean hasPermission(String permission) {
        return console.hasPermission(permission);
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
        return console;
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
        console.sendMessage(
                new TextComponent(
                        message
                )
        );
    }

    @Override
    public void sendMessage(String[] message) {
        for(String text : message) {
            console.sendMessage(
                    new TextComponent(
                            text
                    )
            );
        }
    }

    @Override
    public void sendColoredMessage(String message) {
        console.sendMessage(
                new TextComponent(
                        color(message)
                )
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for(String text : message) {
            console.sendMessage(
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
        return console;
    }
}


