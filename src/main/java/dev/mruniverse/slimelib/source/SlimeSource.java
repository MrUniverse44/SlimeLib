package dev.mruniverse.slimelib.source;

import java.util.UUID;

public interface SlimeSource<T> {

    T getOriginalSource();

    String getName();

    UUID getUniqueId();

    String getId();

    boolean isConsoleSender();

    boolean isPlayer();

    void sendMessage(String message);

    void sendMessage(String[] message);

    void sendColoredMessage(String message);

    void sendColoredMessage(String[] message);

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    boolean hasPermission(String permission);

    T get();
}
