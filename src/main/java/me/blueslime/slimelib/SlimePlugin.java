package me.blueslime.slimelib;

import me.blueslime.slimelib.commands.SlimeCommands;
import me.blueslime.slimelib.exceptions.SlimeLoaderException;
import me.blueslime.slimelib.file.configuration.ConfigurationHandler;
import me.blueslime.slimelib.loader.BaseSlimeLoader;
import me.blueslime.slimelib.logs.SlimeLogs;

/**
 * Provides an interface to a plugin for access to the SlimePlugin
 * @see SlimePluginResource to check where is your plugin directory.
 * @see SlimePlatform to
 */
public interface SlimePlugin<T> extends SlimePluginResource {

    SlimePlatform platform = SlimePlatform.getDetected();

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

    default SlimeCommands<T> getCommands() {
        if (getLoader() == null) {
            new SlimeLoaderException("Commands can't be initialize because the Loader of the main class is not set!").printStackTrace();
        }
        return getLoader().getCommands();
    }

    SlimeLogs getLogs();

    T getPlugin();

    void reload();
}
