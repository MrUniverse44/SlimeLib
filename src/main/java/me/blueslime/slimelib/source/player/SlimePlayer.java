package me.blueslime.slimelib.source.player;

import me.blueslime.slimelib.source.SlimeSource;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SlimePlayer implements SlimeSource<Player> {

    private final String name;

    private final UUID uuid;

    public SlimePlayer(Player player) {
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
    public Player getOriginalSource() {
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

    public String getId() {
        return uuid.toString().replace("-", "");
    }

    @Override
    public void sendMessage(String message) {
        get().sendMessage(message);
    }

    @Override
    public void sendMessage(String[] message) {
        get().sendMessage(message);
    }

    @Override
    public void sendColoredMessage(String message) {
        get().sendMessage(
                color(message)
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for (String text : message) {
            get().sendMessage(
                    color(text)
            );
        }
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public Player get() {
        return Bukkit.getPlayer(uuid);
    }
}
