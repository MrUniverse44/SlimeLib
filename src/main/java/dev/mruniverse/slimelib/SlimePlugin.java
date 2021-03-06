package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import dev.mruniverse.slimelib.loader.BaseSlimeLoader;
import dev.mruniverse.slimelib.logs.SlimeLogs;

/**
 * Provides an interface to a plugin for access to the SlimePlugin
 * @see SlimePluginResource to check where is your plugin directory.
 * @see SlimePlatform to
 */
public interface SlimePlugin<T> extends SlimePluginResource {

    SlimePlatform platform = SlimePlatform.getAutomatically();

    SlimePluginInformation getPluginInformation();

    BaseSlimeLoader<T> getLoader();

    default SlimePlatform getServerType() {
        return platform;
    }

    default ConfigurationHandler getConfigurationHandler(SlimeFiles file) {
        if (getLoader() == null) {
            return null;
        }
        if (getLoader().getFiles() == null) {
            return null;
        }
        return getLoader().getFiles().getConfigurationHandler(file);
    }

    SlimeLogs getLogs();

    T getPlugin();

    void reload();
}
