package dev.mruniverse.slimelib.events.internal.listeners;

import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventCaller;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.converter.bukkit.ping.NormalServerListPing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitListener extends EventCaller<JavaPlugin> implements Listener {

    public BukkitListener(EventExecutor<JavaPlugin> manager) {
        super(manager);
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent event) {

        boolean status = getManager().getEventRegistered().get(EventType.SERVER_LIST_EVENT);

        if (!status) {
            return;
        }

        getManager().getEventManager().callEvent(
                new NormalServerListPing(event)
        );
    }
}
