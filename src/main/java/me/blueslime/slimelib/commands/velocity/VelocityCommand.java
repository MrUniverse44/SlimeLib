package me.blueslime.slimelib.commands.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.slimelib.SlimePlugin;

public class VelocityCommand {
    private final SlimePlugin<ProxyServer> plugin;

    @SuppressWarnings("unchecked")
    public <T> VelocityCommand(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<ProxyServer>) plugin;
    }

    public SlimeCommandsVelocity<ProxyServer> get() {
        return new SlimeCommandsVelocity<>(plugin);
    }
}
