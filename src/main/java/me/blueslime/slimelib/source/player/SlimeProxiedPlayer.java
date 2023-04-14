package me.blueslime.slimelib.source.player;

import me.blueslime.slimelib.source.SlimeSource;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class SlimeProxiedPlayer implements SlimeSource<ProxiedPlayer> {

    private final String name;

    private final UUID uuid;

    public SlimeProxiedPlayer(ProxiedPlayer player) {
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
        return get().hasPermission(permission);
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
    public ProxiedPlayer getOriginalSource() {
        return get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public String getId() {
        return uuid.toString().replace("-", "");
    }

    @Override
    public void sendMessage(String message) {
        get().sendMessage(
                new TextComponent(
                        message
                )
        );
    }

    @Override
    public void sendMessage(String[] message) {
        for(String text : message) {
            get().sendMessage(
                    new TextComponent(
                            text
                    )
            );
        }
    }

    @Override
    public void sendColoredMessage(String message) {
        get().sendMessage(
                new TextComponent(
                        color(message)
                )
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for(String text : message) {
            get().sendMessage(
                    new TextComponent(
                            color(text)
                    )
            );
        }
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public ProxiedPlayer get() {
        return ProxyServer.getInstance().getPlayer(uuid);
    }
}

