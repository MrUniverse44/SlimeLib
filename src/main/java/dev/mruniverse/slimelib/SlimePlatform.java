package dev.mruniverse.slimelib;

public enum SlimePlatform {
    SPIGOT,
    VELOCITY,
    SPONGE,
    BUNGEECORD;

    @SuppressWarnings("unused")
    public boolean isBungee() {
        return (this == BUNGEECORD);
    }
}
