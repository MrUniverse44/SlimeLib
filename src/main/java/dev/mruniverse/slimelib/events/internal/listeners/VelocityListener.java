package dev.mruniverse.slimelib.events.internal.listeners;

import com.velocitypowered.api.proxy.ProxyServer;
import dev.mruniverse.slimelib.events.internal.EventCaller;
import dev.mruniverse.slimelib.events.internal.EventExecutor;

public class VelocityListener extends EventCaller<ProxyServer> {
    public VelocityListener(EventExecutor<ProxyServer> manager) {
        super(manager);
    }
}
