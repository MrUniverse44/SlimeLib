package dev.mruniverse.slimelib.events.internal.platform;

import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.listeners.BungeeListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeEventExecutor extends EventExecutor<Plugin> {

    private final BungeeListener listener = new BungeeListener(this);

    private boolean first = false;

    public <T> BungeeEventExecutor(T plugin) {
        super((Plugin)plugin);
    }

    @Override
    public void register(Object plugin, EventType type) {
        if (!first) {
            first = true;
            ProxyServer.getInstance().getPluginManager().registerListener(
                    getPlugin(),
                    listener
            );
        }
        getEventRegistered().put(type, true);
    }
}
