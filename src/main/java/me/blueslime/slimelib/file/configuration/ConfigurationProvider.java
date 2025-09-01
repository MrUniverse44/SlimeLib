package me.blueslime.slimelib.file.configuration;

import me.blueslime.slimelib.SlimePlatform;
import me.blueslime.slimelib.file.configuration.provider.BukkitConfigurationProvider;
import me.blueslime.slimelib.file.configuration.provider.BungeeConfigurationProvider;
import me.blueslime.slimelib.file.configuration.provider.DefaultConfigurationProvider;
import me.blueslime.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.InputStream;

public interface ConfigurationProvider {

    static ConfigurationProvider newInstance() {
        switch (SlimePlatform.getDetected()) {
            case SPONGE:
            case VELOCITY:
                return new DefaultConfigurationProvider();
            case BUKKIT:
            case PAPER:
            case SPIGOT:
                return new BukkitConfigurationProvider();
            case BUNGEECORD:
            default:
                return new BungeeConfigurationProvider();
        }
    }

    /**
     * Create a controller file, logs are enabled by default.
     * @param logs Slime Logs
     * @param file File to load
     * @param resource Resource file
     * @return Controller file
     */
    default ConfigurationHandler create(SlimeLogs logs, File file, InputStream resource) {
        return create(logs, file, resource, false);
    }

    /**
     * Create a controller file.
     * @param logs Slime Logs
     * @param file File to load
     * @param resource Resource file
     * @param withoutLogs toggle logs in the file loader
     * @return Controller file
     */
    ConfigurationHandler create(SlimeLogs logs, File file, InputStream resource, boolean withoutLogs);

    /**
     * Create a controller file, logs are enabled by default.
     * @param logs Slime Logs
     * @param file File to load
     * @return Controller file
     */
    default ConfigurationHandler create(SlimeLogs logs, File file) {
        return create(logs, file, false);
    }

    /**
     * Create a controller file.
     * @param logs Slime Logs
     * @param file File to load
     * @param withoutLogs toggle logs in the file loader
     * @return Controller file
     */
    ConfigurationHandler create(SlimeLogs logs, File file, boolean withoutLogs);

}
