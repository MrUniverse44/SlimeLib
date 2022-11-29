package dev.mruniverse.slimelib.multiplatform;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.event.PluginLoadEvent;
import dev.mruniverse.slimelib.event.PluginUnloadEvent;
import dev.mruniverse.slimelib.loader.BaseSlimeLoader;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;

public class DefaultSlimeCore<T> extends SlimeCore<T> {
    public DefaultSlimeCore(SlimeLogs logs, File folder, T plugin) {
        super(logs, folder, plugin);
    }

    public DefaultSlimeCore(SlimePlatform platform, File folder, T plugin) {
        super(platform, folder, plugin);
    }

    public DefaultSlimeCore(SlimePlatform platform, SlimeLogs logs, File folder, T plugin) {
        super(platform, logs, folder, plugin);
    }

    public DefaultSlimeCore(SlimePlatform platform, SlimeLogs logs, BaseSlimeLoader<T> loader, File folder, T plugin) {
        super(platform, logs, loader, folder, plugin);
    }

    @Override
    public void onEvent(PluginLoadEvent event) {

    }

    @Override
    public void onEvent(PluginUnloadEvent event) {

    }
}
