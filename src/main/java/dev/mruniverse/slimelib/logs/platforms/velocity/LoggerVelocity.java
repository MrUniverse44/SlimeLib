package dev.mruniverse.slimelib.logs.platforms.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import dev.mruniverse.slimelib.SlimePlugin;

public class LoggerVelocity {

    private final SlimePlugin<ProxyServer> plugin;

    @SuppressWarnings("unchecked")
    public <T> LoggerVelocity(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<ProxyServer>) plugin;
    }

    public SlimeLoggerVelocity getNewInstance() {
        return new SlimeLoggerVelocity(plugin.getPlugin());
    }
}
