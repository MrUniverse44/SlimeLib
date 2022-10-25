package dev.mruniverse.slimelib.source.console;

import dev.mruniverse.slimelib.source.SlimeSource;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import java.util.UUID;

public class SlimeConsoleSpigot implements SlimeSource<ConsoleCommandSender> {

    private final ConsoleCommandSender sender = Bukkit.getServer().getConsoleSender();

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

    @Override
    public ConsoleCommandSender get() {
        return sender;
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
    public ConsoleCommandSender getOriginalSource() {
        return sender;
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
        sender.sendMessage(message);
    }

    @Override
    public void sendMessage(String[] message) {
        sender.sendMessage(message);
    }

    @Override
    public void sendColoredMessage(String message) {
        sender.sendMessage(
                color(message)
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for (String text : message) {
            sender.sendMessage(
                    color(text)
            );
        }
    }

    private String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
