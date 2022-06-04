package dev.mruniverse.slimelib.logs;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.logs.platforms.bungee.LoggerBungee;
import dev.mruniverse.slimelib.logs.platforms.spigot.LoggerSpigot;
import dev.mruniverse.slimelib.logs.platforms.sponge.LoggerSponge;
import dev.mruniverse.slimelib.logs.platforms.velocity.LoggerVelocity;

public class SlimeLog {

    public static <T> SlimeLogs createLogs(SlimePlugin<T> plugin) {
        SlimePlatform platform = SlimePlatform.getAutomatically();
        switch (platform) {
            case BUNGEECORD:
                return new LoggerBungee(plugin).getNewInstance();
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance();
            case SPONGE:
                return new LoggerSponge().getNewInstance();
            default:
            case SPIGOT:
                return new LoggerSpigot(plugin).getNewInstance();
        }
    }

    public static <T> SlimeLogs createLogs(SlimePlatform platform, SlimePlugin<T> plugin) {
        switch (platform) {
            case BUNGEECORD:
                return new LoggerBungee(plugin).getNewInstance();
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance();
            case SPONGE:
                return new LoggerSponge().getNewInstance();
            default:
            case SPIGOT:
                return new LoggerSpigot(plugin).getNewInstance();
        }
    }

}
