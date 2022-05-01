package dev.mruniverse.slimelib.logs.platforms.bungee;

import dev.mruniverse.slimelib.SlimePlugin;
import net.md_5.bungee.api.plugin.Plugin;

public class LoggerBungee {

    private final SlimePlugin<Plugin> plugin;

    @SuppressWarnings("unchecked")
    public <T> LoggerBungee(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<Plugin>) plugin;
    }

    public SlimeLoggerBungee getNewInstance() {
        return new SlimeLoggerBungee(plugin.getPlugin());
    }

}
