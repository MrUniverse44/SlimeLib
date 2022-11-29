package dev.mruniverse.slimelib.multiplatform.init.bukkit;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.event.PluginLoadEvent;
import dev.mruniverse.slimelib.event.PluginUnloadEvent;
import dev.mruniverse.slimelib.multiplatform.DefaultSlimeCore;
import dev.mruniverse.slimelib.multiplatform.SlimeCore;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("unused")
public final class BukkitCore extends Plugin {

    private static BukkitCore bukkitCore;

    private SlimeCore<Plugin> instance;

    @Override
    public void onEnable() {
        bukkitCore = this;

        instance = new DefaultSlimeCore<>(
                SlimePlatform.SPIGOT,
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

    public static BukkitCore getInstance() {
        return bukkitCore;
    }
}