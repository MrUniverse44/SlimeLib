package dev.mruniverse.guardianstorageapi.enums;

public enum ControlType {
    SPIGOT,
    BUNGEECORD;

    public boolean isBungee() {
        return (this == BUNGEECORD);
    }
}
