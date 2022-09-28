package dev.mruniverse.slimelib.logs;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.logs.platforms.bungee.LoggerBungee;
import dev.mruniverse.slimelib.logs.platforms.bungee.SlimeLoggerBungee;
import dev.mruniverse.slimelib.logs.platforms.spigot.LoggerSpigot;
import dev.mruniverse.slimelib.logs.platforms.spigot.SlimeLoggerSpigot;
import dev.mruniverse.slimelib.logs.platforms.sponge.LoggerSponge;
import dev.mruniverse.slimelib.logs.platforms.sponge.SlimeLoggerSponge;
import dev.mruniverse.slimelib.logs.platforms.velocity.LoggerVelocity;
import dev.mruniverse.slimelib.logs.platforms.velocity.SlimeLoggerVelocity;

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

    /**
     * Only if you are in velocity put the ProxyServer instance
     * @param proxyServer only for velocity, use null for other platforms
     * @return SlimeLogs
     */
    public static SlimeLogs fromLegacy(Object proxyServer) {
        SlimePlatform platform = SlimePlatform.getAutomatically();
        switch (platform) {
            case BUNGEECORD:
                return new SlimeLoggerBungee();
            case VELOCITY:
                return new SlimeLoggerVelocity(proxyServer);
            case SPONGE:
                return new SlimeLoggerSponge();
            default:
            case SPIGOT:
                return new SlimeLoggerSpigot();
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
            case BUNGEECORD:
                return new LoggerBungee(plugin).getNewInstance(pluginName);
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance(pluginName);
            case SPONGE:
                return new LoggerSponge().getNewInstance(pluginName);
            default:
            case SPIGOT:
                return new LoggerSpigot(plugin).getNewInstance(pluginName);
        }
    }

    public static <T> SlimeLogs createLogs(SlimePlugin<T> plugin, String pluginName) {
        SlimePlatform platform = SlimePlatform.getAutomatically();
        switch (platform) {
            case BUNGEECORD:
                return new LoggerBungee(plugin).getNewInstance(pluginName);
            case VELOCITY:
                return new LoggerVelocity(plugin).getNewInstance(pluginName);
            case SPONGE:
                return new LoggerSponge().getNewInstance(pluginName);
            default:
            case SPIGOT:
                return new LoggerSpigot(plugin).getNewInstance(pluginName);
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

    public SlimeLoggerProperties getProperties() {
        return properties;
    }

    public String getContainIdentifier() {
        return containIdentifier;
    }

    public String getHidePackage() {
        return hidePackage;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setContainIdentifier(String containIdentifier) {
        this.containIdentifier = containIdentifier;
    }

    public void setHidePackage(String hidePackage) {
        this.hidePackage = hidePackage;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public void setProperties(SlimeLoggerProperties properties) {
        this.properties = properties;
    }

}
