package dev.mruniverse.slimelib.event;

import dev.mruniverse.slimelib.multiplatform.SlimeCore;

public class PluginLoadEvent {

    private final String minecraftVersion;
    private final SlimeCore<?> core;

    public PluginLoadEvent(SlimeCore<?> core) {
        this.core = core;
        this.minecraftVersion = "Coming soon";
    }

    public String getMinecraftVersion() {
        return minecraftVersion;
    }

    public SlimeCore<?> getCore() {
        return core;
    }
}
