package me.blueslime.slimelib.logs;

import lombok.Getter;
import lombok.Setter;
import me.blueslime.slimelib.SlimePlatform;
import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.logs.platforms.bungee.LoggerBungee;
import me.blueslime.slimelib.logs.platforms.bungee.SlimeLoggerBungee;
import me.blueslime.slimelib.logs.platforms.spigot.LoggerSpigot;
import me.blueslime.slimelib.logs.platforms.spigot.SlimeLoggerSpigot;
import me.blueslime.slimelib.logs.platforms.sponge.LoggerSponge;
import me.blueslime.slimelib.logs.platforms.sponge.SlimeLoggerSponge;
import me.blueslime.slimelib.logs.platforms.velocity.LoggerVelocity;
import me.blueslime.slimelib.logs.platforms.velocity.SlimeLoggerVelocity;

@Setter
@Getter
@SuppressWarnings({"unused", "deprecation"})
public class SlimeLogger {

    private SlimeLoggerProperties properties;

    private String containIdentifier = "blueslime";

    private String hidePackage = "me.blueslime.";

    private String pluginName = "SlimeLib";

    public SlimeLogger() {
        properties = new SlimeLoggerProperties();
    }

    public SlimeLogger(SlimeLoggerProperties properties) {
        this.properties = properties;
    }

    public static <T> SlimeLogs createLogs(SlimePlugin<T> plugin) {
        SlimePlatform platform = SlimePlatform.getDetected();
        return switch (platform) {
            case VELOCITY -> new LoggerVelocity(plugin).getNewInstance();
            case SPONGE -> new LoggerSponge().getNewInstance();
            case SPIGOT, PAPER, BUKKIT -> new LoggerSpigot(plugin).getNewInstance();
            default -> new LoggerBungee(plugin).getNewInstance();
        };
    }

    public void rename(String newName) {
        properties.getPrefixes().changeMainText(newName);
    }

    public void rename(String oldName, String newName) {
        properties.getPrefixes().changeMainText(newName);
    }

    /**
     * Only if you are in velocity put the ProxyServer instance
     * @param proxyServer only for velocity, use null for other platforms
     * @return SlimeLogs
     */
    public static SlimeLogs fromLegacy(Object proxyServer) {
        SlimePlatform platform = SlimePlatform.getDetected();
        return switch (platform) {
            case VELOCITY -> new SlimeLoggerVelocity(proxyServer);
            case SPONGE -> new SlimeLoggerSponge();
            case SPIGOT, PAPER, BUKKIT -> new SlimeLoggerSpigot();
            default -> new SlimeLoggerBungee();
        };
    }

    /**
     * Only if you are in velocity put the ProxyServer instance
     * @param proxyServer only for velocity, use null for other platforms
     * @return SlimeLogs
     */
    public static SlimeLogs fromLegacy(Object proxyServer, String name) {
        SlimeLogs log = fromLegacy(proxyServer);
        log.getProperties().getPrefixes().changeMainText(name);
        return log;
    }

    public static <T> SlimeLogs createLogs(SlimePlatform platform, SlimePlugin<T> plugin, String pluginName) {
        return switch (platform) {
            case VELOCITY -> new LoggerVelocity(plugin).getNewInstance(pluginName);
            case SPONGE -> new LoggerSponge().getNewInstance(pluginName);
            case SPIGOT, PAPER, BUKKIT -> new LoggerSpigot(plugin).getNewInstance(pluginName);
            default -> new LoggerBungee(plugin).getNewInstance(pluginName);
        };
    }

    public static <T> SlimeLogs createLogs(SlimePlugin<T> plugin, String pluginName) {
        SlimePlatform platform = SlimePlatform.getDetected();
        return switch (platform) {
            case VELOCITY -> new LoggerVelocity(plugin).getNewInstance(pluginName);
            case SPONGE -> new LoggerSponge().getNewInstance(pluginName);
            case SPIGOT, PAPER, BUKKIT -> new LoggerSpigot(plugin).getNewInstance(pluginName);
            default -> new LoggerBungee(plugin).getNewInstance(pluginName);
        };
    }

    public static <T> SlimeLogs createLogs(SlimePlatform platform, SlimePlugin<T> plugin) {
        return switch (platform) {
            case VELOCITY -> new LoggerVelocity(plugin).getNewInstance();
            case SPONGE -> new LoggerSponge().getNewInstance();
            case SPIGOT, PAPER, BUKKIT -> new LoggerSpigot(plugin).getNewInstance();
            default -> new LoggerBungee(plugin).getNewInstance();
        };
    }

}
