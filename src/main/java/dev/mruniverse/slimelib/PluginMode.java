package dev.mruniverse.slimelib;

public enum PluginMode {
    SPIGOT,
    VELOCITY,
    BUNGEECORD;

    @SuppressWarnings("unused")
    public boolean isBungee() {
        return (this == BUNGEECORD);
    }
}
