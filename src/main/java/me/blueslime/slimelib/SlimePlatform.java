package me.blueslime.slimelib;

import me.blueslime.slimelib.exceptions.SlimePlatformNotFoundException;
import me.blueslime.slimelib.file.configuration.provider.Provider;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;

public enum SlimePlatform {
    /**
     * Please change it to {@link #SPIGOT} or {@link #PAPER} depending on your case.
     */
    @Deprecated
    BUKKIT,
    SPIGOT,
    VELOCITY,
    SPONGE,
    BUNGEECORD,
    PAPER,
    FABRIC,
    FORGE,
    NEO_FORGE;

    private static SlimePlatform DETECTED = null;

    public static SlimePlatform getDetected() {
        if (DETECTED == null) {
            DETECTED = getAutomatically();
        }
        return DETECTED;
    }

    @SuppressWarnings("unused")
    public boolean isBungee() {
        return (this == BUNGEECORD);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static SlimePlatform getAutomatically() {
        if (validClassOutsidePlugins("com.velocitypowered.api.proxy.ProxyServer")) {
            return SlimePlatform.VELOCITY;
        }
        if (validClassOutsidePlugins("net.md_5.bungee.api.ProxyServer")) {
            return SlimePlatform.BUNGEECORD;
        }
        if (validClassOutsidePlugins("com.destroystokyo.paper.PaperConfig")) {
            return SlimePlatform.PAPER;
        }
        if (validClassOutsidePlugins("org.bukkit.Bukkit")) {
            return SlimePlatform.SPIGOT;
        }
        if (validClassOutsidePlugins("org.spongepowered.api.Sponge")) {
            return SPONGE;
        }
        if (validClassOutsidePlugins("net.fabricmc.loader.api.FabricLoader")) {
            return SlimePlatform.FABRIC;
        }
        if (validClassOutsidePlugins("net.minecraftforge.fml.ModList")) {
            return SlimePlatform.FORGE;
        }
        if (validClassOutsidePlugins("net.neoforged.someclass")) {
            return SlimePlatform.NEO_FORGE;
        }
        new SlimePlatformNotFoundException().printStackTrace();
        return SlimePlatform.BUNGEECORD;
    }

    /**
     * Prevent wrong detections
     */
    private static boolean validClassOutsidePlugins(String className) {
        try {
            Class<?> clazz = Class.forName(className, false, SlimePlatform.class.getClassLoader());
            CodeSource cs = clazz.getProtectionDomain().getCodeSource();

            if (cs == null) {
                return true;
            }

            URL loc = cs.getLocation();
            if (loc == null) {
                return true;
            }

            URI uri = loc.toURI();
            Path jarPath = Paths.get(uri);
            File file = jarPath.toFile();

            return !file.getAbsolutePath().toLowerCase().contains(File.separator + "plugins" + File.separator);
        } catch (Throwable ignored) {
            return false;
        }
    }

    public Provider getProvider() {
        return switch (this) {
            case SPONGE, VELOCITY, FORGE, FABRIC, NEO_FORGE -> Provider.DEFAULT;
            case PAPER, SPIGOT, BUKKIT -> Provider.BUKKIT;
            default -> Provider.BUNGEE_CORD;
        };
    }

    private static Class<?> getClass(String location) {
        try {
            return Class.forName(location);
        } catch (ClassNotFoundException ignored) {
            return null;
        }
    }
}
