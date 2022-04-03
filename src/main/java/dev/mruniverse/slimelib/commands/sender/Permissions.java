package dev.mruniverse.slimelib.commands.sender;

public interface Permissions {

    /**
     * Check if the Sender has a specified permission
     *
     * @param permission String - Permission to be checked in the sender
     */
    boolean hasPermission(String permission);
}
