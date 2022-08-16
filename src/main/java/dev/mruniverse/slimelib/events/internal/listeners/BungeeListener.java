package dev.mruniverse.slimelib.events.internal.listeners;

import dev.mruniverse.slimelib.events.internal.EventCaller;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeListener extends EventCaller<Plugin> implements Listener {
    public BungeeListener(EventExecutor<Plugin> manager) {
        super(manager);
    }
}
