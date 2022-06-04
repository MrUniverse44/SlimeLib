package dev.mruniverse.slimelib.commands.sender.console;

import dev.mruniverse.slimelib.commands.sender.Sender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;

public class SlimeConsoleSponge implements Sender {

    Server server;

    public SlimeConsoleSponge() {
        this.server = Sponge.server();
    }

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    @Override
    public boolean hasPermission(String permission) {
        return true;
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
        server.sendMessage(
                Component.text(message)
        );
    }

    @Override
    public void sendMessage(String[] message) {
        for (String text : message) {
            server.sendMessage(
                    Component.text(text)
            );
        }
    }

    @Override
    public void sendColoredMessage(String message) {
        server.sendMessage(
                color(message)
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for (String text : message) {
            server.sendMessage(
                    color(text)
            );
        }
    }

    private Component color(String message) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(message);
    }

    public Server get() {
        return server;
    }
}
