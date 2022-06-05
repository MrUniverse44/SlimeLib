package dev.mruniverse.slimelib.logs.platforms.spigot;

import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.logs.SlimeLogger;
import org.bukkit.plugin.java.JavaPlugin;

public class LoggerSpigot {

    private final SlimePlugin<JavaPlugin> plugin;

    @SuppressWarnings("unchecked")
    public <T> LoggerSpigot(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<JavaPlugin>) plugin;
    }

    public SlimeLoggerSpigot getNewInstance() {
        return new SlimeLoggerSpigot(plugin.getPlugin());
    }

    public SlimeLoggerSpigot getNewInstance(String pluginName) {
        SlimeLogger logger = new SlimeLogger();
        logger.setPluginName(pluginName);
        return new SlimeLoggerSpigot(
                plugin.getPlugin(),
                logger
        );
    }

}
