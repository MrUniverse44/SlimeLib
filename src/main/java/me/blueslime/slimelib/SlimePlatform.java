package me.blueslime.slimelib;

import me.blueslime.slimelib.exceptions.SlimePlatformNotFoundException;
import me.blueslime.slimelib.file.configuration.provider.Provider;
import org.jetbrains.annotations.Nullable;

public enum SlimePlatform {
    BUKKIT,
    VELOCITY,
    SPONGE,
    BUNGEECORD;

    @SuppressWarnings("unused")
    public boolean isBungee() {
        return (this == BUNGEECORD);
    }

    public static SlimePlatform getAutomatically() {
        if (getClass("org.bukkit.Bukkit") != null) {
            return SlimePlatform.BUKKIT;
        }
        if (getClass("net.md_5.bungee.api.plugin.Plugin") != null) {
            return SlimePlatform.BUNGEECORD;
        }
        if (getClass("com.velocitypowered.api.proxy.ProxyServer") != null) {
            return SlimePlatform.VELOCITY;
        }
        if (getClass("org.spongepowered.api.Sponge") != null) {
            return SlimePlatform.SPONGE;
        }
        new SlimePlatformNotFoundException().printStackTrace();
        return BUNGEECORD;
    }

    public Provider getProvider() {
        switch (this) {
            case BUNGEECORD:
                return Provider.BUNGEE_CORD;
            default:
            case SPONGE:
            case VELOCITY:
                return Provider.DEFAULT;
            case BUKKIT:
                return Provider.BUKKIT;
        }
    }

    private static @Nullable Class<?> getClass(String location) {
        try {
            return Class.forName(location);
        } catch (ClassNotFoundException ignored) {
            return null;
        }
    }
}
