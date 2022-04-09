package dev.mruniverse.slimelib;

public enum SlimePlatform {
    SPIGOT,
    VELOCITY,
    BUNGEECORD;

    @SuppressWarnings("unused")
    public boolean isBungee() {
        return (this == BUNGEECORD);
    }
}
