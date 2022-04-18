package dev.mruniverse.slimelib.commands.sender.player;

import dev.mruniverse.slimelib.commands.sender.Sender;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class SlimeProxiedPlayer implements Sender {

    ProxiedPlayer player;

    String name;

    UUID uuid;

    public SlimeProxiedPlayer(ProxiedPlayer player) {
        this.player = player;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
    }

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean isConsoleSender() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public String getId() {
        return uuid.toString().replace("-", "");
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(
                new TextComponent(
                        message
                )
        );
    }

    @Override
    public void sendMessage(String[] message) {
        for(String text : message) {
            player.sendMessage(
                    new TextComponent(
                            text
                    )
            );
        }
    }

    @Override
    public void sendColoredMessage(String message) {
        player.sendMessage(
                new TextComponent(
                        color(message)
                )
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for(String text : message) {
            player.sendMessage(
                    new TextComponent(
                            color(text)
                    )
            );
        }
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public ProxiedPlayer get() {
        return player;
    }
}

