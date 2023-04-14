package me.blueslime.slimelib.logs.platforms.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.logs.SlimeLogger;

public class LoggerVelocity {

    private final SlimePlugin<ProxyServer> plugin;

    @SuppressWarnings("unchecked")
    public <T> LoggerVelocity(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<ProxyServer>) plugin;
    }

    public SlimeLoggerVelocity getNewInstance() {
        return new SlimeLoggerVelocity(plugin.getPlugin());
    }

    public SlimeLoggerVelocity getNewInstance(String pluginName) {
        SlimeLogger logger = new SlimeLogger();
        logger.setPluginName(pluginName);
        return new SlimeLoggerVelocity(
                plugin.getPlugin(),
                logger
        );
    }
}
