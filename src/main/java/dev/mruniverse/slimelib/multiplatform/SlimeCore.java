package dev.mruniverse.slimelib.multiplatform;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimePluginInformation;
import dev.mruniverse.slimelib.event.PluginLoadEvent;
import dev.mruniverse.slimelib.event.PluginUnloadEvent;
import dev.mruniverse.slimelib.loader.BaseSlimeLoader;
import dev.mruniverse.slimelib.loader.DefaultSlimeLoader;
import dev.mruniverse.slimelib.logs.SlimeLogger;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;

public abstract class SlimeCore<T> implements SlimePlugin<T> {

    private final SlimePluginInformation information;
    private final BaseSlimeLoader<T> loader;
    private final SlimePlatform type;
    private final SlimeLogs logs;
    private final File folder;
    private final T plugin;

    public SlimeCore(SlimeLogs logs, File folder, T plugin) {
        this(SlimePlatform.getAutomatically(), logs, null, folder, plugin);
    }

    public SlimeCore(SlimePlatform platform, File folder, T plugin) {
        this(platform, null, null, folder, plugin);
    }

    public SlimeCore(SlimePlatform platform, SlimeLogs logs, File folder, T plugin) {
        this(platform, logs, null, folder, plugin);
    }

    public SlimeCore(SlimePlatform platform, SlimeLogs logs, BaseSlimeLoader<T> loader, File folder, T plugin) {
        this.type        = platform;

        if (logs != null) {
            this.logs = logs;
        } else {
            this.logs = SlimeLogger.createLogs(platform, this, "SlimeCore");
        }

        this.information = new SlimePluginInformation(
                type,
                plugin
        );

        this.folder      = folder;
        this.plugin      = plugin;

        if (loader != null) {
            this.loader = loader;
        } else {
            this.loader = new DefaultSlimeLoader<>(this);
        }

        onEvent(
                new PluginLoadEvent(
                        this
                )
        );
    }

    @Override
    public SlimePluginInformation getPluginInformation() {
        return information;
    }

    /**
     * This method is called when the plugin is reloaded or being loaded
     * @param event - Event caller
     */
    public abstract void onEvent(PluginLoadEvent event);

    /**
     * This method is called when the plugin is reloaded or being unloaded
     * @param event - Event caller
     */
    public abstract void onEvent(PluginUnloadEvent event);

    @Override
    public BaseSlimeLoader<T> getLoader() {
        return loader;
    }

    @Override
    public SlimePlatform getServerType() {
        return type;
    }

    @Override
    public SlimeLogs getLogs() {
        return logs;
    }

    @Override
    public T getPlugin() {
        return plugin;
    }

    @Override
    public void reload() {
        onEvent(
                new PluginUnloadEvent(
                        this
                )
        );
        onEvent(
                new PluginLoadEvent(
                        this
                )
        );
    }

    @Override
    public File getDataFolder() {
        return folder;
    }
}
