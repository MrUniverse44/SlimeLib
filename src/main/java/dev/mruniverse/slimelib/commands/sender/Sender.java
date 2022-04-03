package dev.mruniverse.slimelib.commands.sender;

public interface Sender extends Permissions {

    String getName();

    void sendMessage(String message);

    void sendMessage(String[] message);

    void sendColoredMessage(String message);

    void sendColoredMessage(String[] message);

}
