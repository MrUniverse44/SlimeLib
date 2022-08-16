package dev.mruniverse.slimelib.events.internal.platform;

import com.velocitypowered.api.proxy.ProxyServer;
import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.listeners.VelocityListener;

public class VelocityEventExecutor extends EventExecutor<ProxyServer> {

    private final VelocityListener listener = new VelocityListener(this);

    private boolean first = false;

    public <T> VelocityEventExecutor(T plugin) {
        super((ProxyServer) plugin);
    }

    @Override
    public void register(Object plugin, EventType type) {
        if (!first) {
            first = true;
            getPlugin().getEventManager().register(
                    plugin,
                    listener
            );
        }
        getEventRegistered().put(type, true);
    }
}
