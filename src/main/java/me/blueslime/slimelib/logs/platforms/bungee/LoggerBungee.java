package me.blueslime.slimelib.logs.platforms.bungee;

import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.logs.SlimeLogger;
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

    public SlimeLoggerBungee getNewInstance(String pluginName) {
        SlimeLogger logger = new SlimeLogger();
        logger.setPluginName(pluginName);
        return new SlimeLoggerBungee(
                plugin.getPlugin(),
                logger
        );
    }

}
