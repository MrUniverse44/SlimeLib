package dev.mruniverse.slimelib.commands.sender.player;

import dev.mruniverse.slimelib.commands.sender.Sender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class SlimeSpongePlayer implements Sender {

    Player player;

    String name;

    UUID uuid;

    public SlimeSpongePlayer(Player player) {
        this.player = player;
        this.name = player.name();
        this.uuid = player.uniqueId();
    }

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    @Override
    public boolean hasPermission(String permission) {
        return false;
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

    public Player get() {
        return player;
    }
}
