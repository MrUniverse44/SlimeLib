package me.blueslime.slimelib.file.configuration.provider;

import me.blueslime.slimelib.file.configuration.ConfigurationHandler;
import me.blueslime.slimelib.file.configuration.ConfigurationProvider;
import me.blueslime.slimelib.file.configuration.handlers.other.PluginConfigurationHandler;
import me.blueslime.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.InputStream;

public class DefaultConfigurationProvider implements ConfigurationProvider {

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
        return new PluginConfigurationHandler(
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
        return new PluginConfigurationHandler(
                logs,
                file
        );
    }

}
