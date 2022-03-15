package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.loader.SlimeLoader;
import dev.mruniverse.slimelib.logs.SlimeLogs;

public interface SlimePlugin extends SlimePluginResource {

    PluginMode getServerType();

    SlimeLogs getSlimeLogs();

    SlimeLoader getSlimeLoader();

    void reload();
}
