package dev.mruniverse.slimelib.commands.sender;

public interface Sender extends Permissions {

    String getName();

    boolean isConsoleSender();

    boolean isPlayer();

    void sendMessage(String message);

    void sendMessage(String[] message);

    void sendColoredMessage(String message);

    void sendColoredMessage(String[] message);

}
