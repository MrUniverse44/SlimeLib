package dev.mruniverse.slimelib.file.configuration.provider;

import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import dev.mruniverse.slimelib.file.configuration.ConfigurationProvider;
import dev.mruniverse.slimelib.file.configuration.handlers.bungee.BungeeConfigurationHandler;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.InputStream;

public class BungeeConfigurationProvider implements ConfigurationProvider {

    /**
     * Create a controller file.
     *
     * @param logs     Slime Logs
     * @param file     File to load
     * @param resource Resource file
     * @return Controller file
     */
    @Override
    public ConfigurationHandler create(SlimeLogs logs, File file, InputStream resource) {
        return new BungeeConfigurationHandler(
                logs,
                file,
                resource
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
    public ConfigurationHandler create(SlimeLogs logs, File file) {
        return new BungeeConfigurationHandler(
                logs,
                file
        );
    }

}
