package dev.mruniverse.guardianstorageapi.enums;

public enum ControlType {
    SPIGOT,
    BUNGEECORD;

    @SuppressWarnings("unused")
    public boolean isBungee() {
        return (this == BUNGEECORD);
    }
}
