package dev.mruniverse.slimelib.multiplatform.init.bungee;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.event.PluginLoadEvent;
import dev.mruniverse.slimelib.event.PluginUnloadEvent;
import dev.mruniverse.slimelib.multiplatform.DefaultSlimeCore;
import dev.mruniverse.slimelib.multiplatform.SlimeCore;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("unused")
public final class BungeeCore extends Plugin {

    private static BungeeCore bungeeCore;

    private SlimeCore<Plugin> instance;

    @Override
    public void onEnable() {
        bungeeCore = this;

        instance = new DefaultSlimeCore<>(
                SlimePlatform.BUNGEECORD,
                getDataFolder(),
                this
        );

        instance.onEvent(
                new PluginLoadEvent(
                        instance
                )
        );
    }

    @Override
    public void onDisable() {
        instance.onEvent(
                new PluginUnloadEvent(
                        instance
                )
        );
    }

    public static BungeeCore getInstance() {
        return bungeeCore;
    }
}