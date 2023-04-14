package me.blueslime.slimelib.source.console;

import me.blueslime.slimelib.source.SlimeSource;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;

import java.util.UUID;

public class SlimeConsoleSponge implements SlimeSource<Server> {
    private static SlimeConsoleSponge SPONGE_CONSOLE = null;
    public static SlimeConsoleSponge instance() {
        if (SPONGE_CONSOLE == null) {
            SPONGE_CONSOLE = new SlimeConsoleSponge();
        }
        return SPONGE_CONSOLE;
    }

    private Server sender() {
        return Sponge.server();
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
    public Server getOriginalSource() {
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
                Component.text(message)
        );
    }

    @Override
    public void sendMessage(String[] message) {
        for (String text : message) {
            sender().sendMessage(
                    Component.text(text)
            );
        }
    }

    @Override
    public void sendColoredMessage(String message) {
        sender().sendMessage(
                color(message)
        );
    }

    @Override
    public void sendColoredMessage(String[] message) {
        for (String text : message) {
            sender().sendMessage(
                    color(text)
            );
        }
    }

    private Component color(String message) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(message);
    }

    public Server get() {
        return sender();
    }
}
