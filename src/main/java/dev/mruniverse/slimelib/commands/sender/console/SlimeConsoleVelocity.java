package dev.mruniverse.slimelib.commands.sender.console;

import com.velocitypowered.api.proxy.ConsoleCommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.mruniverse.slimelib.commands.sender.Sender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class SlimeConsoleVelocity implements Sender {

    ConsoleCommandSource player;

    public SlimeConsoleVelocity(ProxyServer server) {
        this.player = server.getConsoleCommandSource();
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
        player.sendMessage(
                Component.text(message)
        );
    }

    @Override
    public void sendMessage(String[] message) {
        for (String text : message) {
            player.sendMessage(
                    Component.text(text)
            );
        }
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

    private Component color(String message) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(message);
    }

    public ConsoleCommandSource get() {
        return player;
    }
}


