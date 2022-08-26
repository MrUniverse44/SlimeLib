package dev.mruniverse.slimelib.source.player;

import dev.mruniverse.slimelib.source.SlimeSource;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SlimePlayer implements SlimeSource<Player> {

    private final Player player;

    private final String name;

    private final UUID uuid;

    public SlimePlayer(Player player) {
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
    public Player getOriginalSource() {
        return player;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    public String getId() {
        return uuid.toString().replace("-", "");
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] message) {
        player.sendMessage(message);
    }

    @Override
    public void sendColoredMessage(String message) {
        player.sendMessage(
                color(message)
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for (String text : message) {
            player.sendMessage(
                    color(text)
            );
        }
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public Player get() {
        return player;
    }
}
