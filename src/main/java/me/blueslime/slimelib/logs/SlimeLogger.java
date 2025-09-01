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
@SuppressWarnings("unused")
public class SlimeLogger {

    private SlimeLoggerProperties properties;

    private String containIdentifier = "mruniverse";

    private String hidePackage = "dev.mruniverse.slimelib.";

    private String pluginName = "SlimeLib";

    public SlimeLogger() {
        properties = new SlimeLoggerProperties();
    }

    public SlimeLogger(SlimeLoggerProperties properties) {
        this.properties = properties;
    }

    public static <T> SlimeLogs createLogs(SlimePlugin<T> plugin) {
        SlimePlatform platform = SlimePlatform.getDetected();
        switch (platform) {
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance();
            case SPONGE:
                return new LoggerSponge().getNewInstance();
            case SPIGOT:
            case PAPER:
            case BUKKIT:
                return new LoggerSpigot(plugin).getNewInstance();
            case BUNGEECORD:
            default:
                return new LoggerBungee(plugin).getNewInstance();
        }
    }

    /**
     * Only if you are in velocity put the ProxyServer instance
     * @param proxyServer only for velocity, use null for other platforms
     * @return SlimeLogs
     */
    public static SlimeLogs fromLegacy(Object proxyServer) {
        SlimePlatform platform = SlimePlatform.getDetected();
        switch (platform) {
            case VELOCITY:
                return new SlimeLoggerVelocity(proxyServer);
            case SPONGE:
                return new SlimeLoggerSponge();
            case SPIGOT:
            case PAPER:
            case BUKKIT:
                return new SlimeLoggerSpigot();
            case BUNGEECORD:
            default:
                return new SlimeLoggerBungee();
        }
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
        switch (platform) {
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance(pluginName);
            case SPONGE:
                return new LoggerSponge().getNewInstance(pluginName);
            case SPIGOT:
            case PAPER:
            case BUKKIT:
                return new LoggerSpigot(plugin).getNewInstance(pluginName);
            case BUNGEECORD:
            default:
                return new LoggerBungee(plugin).getNewInstance(pluginName);
        }
    }

    public static <T> SlimeLogs createLogs(SlimePlugin<T> plugin, String pluginName) {
        SlimePlatform platform = SlimePlatform.getDetected();
        switch (platform) {
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance(pluginName);
            case SPONGE:
                return new LoggerSponge().getNewInstance(pluginName);
            case SPIGOT:
            case PAPER:
            case BUKKIT:
                return new LoggerSpigot(plugin).getNewInstance(pluginName);
            case BUNGEECORD:
            default:
                return new LoggerBungee(plugin).getNewInstance(pluginName);

        }
    }

    public static <T> SlimeLogs createLogs(SlimePlatform platform, SlimePlugin<T> plugin) {
        switch (platform) {
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance();
            case SPONGE:
                return new LoggerSponge().getNewInstance();
            case SPIGOT:
            case PAPER:
            case BUKKIT:
                return new LoggerSpigot(plugin).getNewInstance();
            case BUNGEECORD:
            default:
                return new LoggerBungee(plugin).getNewInstance();

        }
    }

}
