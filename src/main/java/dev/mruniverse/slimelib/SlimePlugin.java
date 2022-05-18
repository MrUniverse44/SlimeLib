package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.loader.BaseSlimeLoader;
import dev.mruniverse.slimelib.logs.SlimeLogs;

/**
 * Provides an interface to a plugin for access to the SlimePlugin
 * @see SlimePluginResource to check where is your plugin directory.
 */
public interface SlimePlugin<T> extends SlimePluginResource {

    SlimePluginInformation getPluginInformation();

    SlimePlatform getServerType();

    SlimeLogs getLogs();

    BaseSlimeLoader<T> getLoader();

    T getPlugin();

    void reload();
}
