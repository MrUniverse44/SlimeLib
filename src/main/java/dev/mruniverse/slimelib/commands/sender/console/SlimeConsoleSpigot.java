package dev.mruniverse.slimelib.commands.sender.console;

import dev.mruniverse.slimelib.commands.sender.Sender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class SlimeConsoleSpigot implements Sender {

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
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isConsoleSender() {
        return true;
    }

    @Override
    public String getName() {
        return "Console";
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
