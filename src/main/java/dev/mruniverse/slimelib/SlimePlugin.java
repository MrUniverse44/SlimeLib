package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.logs.SlimeLogs;

public interface SlimePlugin extends SlimePluginResource {

    PluginMode getServerType();

    SlimeLogs getLogs();

    void reload();
}
