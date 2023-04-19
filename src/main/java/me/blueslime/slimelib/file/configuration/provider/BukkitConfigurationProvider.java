package me.blueslime.slimelib.file.configuration.provider;

import me.blueslime.slimelib.file.configuration.ConfigurationHandler;
import me.blueslime.slimelib.file.configuration.ConfigurationProvider;
import me.blueslime.slimelib.file.configuration.handlers.bukkit.BukkitConfigurationHandler;
import me.blueslime.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.InputStream;

public class BukkitConfigurationProvider implements ConfigurationProvider {
    /**
     * Create a controller file.
     *
     * @param logs     Slime Logs
     * @param file     File to load
     * @param resource Resource file
     * @return Controller file
     */
    @Override
    public ConfigurationHandler create(SlimeLogs logs, File file, InputStream resource, boolean withoutLogs) {
        return new BukkitConfigurationHandler(
                logs,
                file,
                resource,
                withoutLogs
        );
    }

    /**
     * Create a controller file.
     *
     * @param logs Slime Logs
     * @param file File to load
     * @return Controller file
     */
    @Override
    public ConfigurationHandler create(SlimeLogs logs, File file, boolean withoutLogs) {
        return new BukkitConfigurationHandler(
                logs,
                file,
                withoutLogs
        );
    }
}
