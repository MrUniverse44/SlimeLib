package dev.mruniverse.slimelib.file.configuration;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.file.configuration.provider.BukkitConfigurationProvider;
import dev.mruniverse.slimelib.file.configuration.provider.BungeeConfigurationProvider;
import dev.mruniverse.slimelib.file.configuration.provider.DefaultConfigurationProvider;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.InputStream;

public interface ConfigurationProvider {

    static ConfigurationProvider newInstance() {
        switch (SlimePlatform.getAutomatically()) {
            case SPONGE:
            case VELOCITY:
                return new DefaultConfigurationProvider();
            default:
            case BUNGEECORD:
                return new BungeeConfigurationProvider();
            case SPIGOT:
                return new BukkitConfigurationProvider();
        }
    }

    /**
     * Create a controller file.
     * @param logs Slime Logs
     * @param file File to load
     * @param resource Resource file
     * @return Controller file
     */
    ConfigurationHandler create(SlimeLogs logs, File file, InputStream resource);

    /**
     * Create a controller file.
     * @param logs Slime Logs
     * @param file File to load
     * @return Controller file
     */
    ConfigurationHandler create(SlimeLogs logs, File file);

}
