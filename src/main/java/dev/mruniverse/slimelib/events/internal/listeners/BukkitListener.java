package dev.mruniverse.slimelib.events.internal.listeners;

import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventCaller;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.converter.bukkit.ping.NormalServerListPing;
import dev.mruniverse.slimelib.events.internal.converter.bukkit.player.PlayerJoin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        boolean status = getManager().getEventRegistered().get(EventType.PLAYER_JOIN_EVENT);

        if (!status) {
            return;
        }

        getManager().getEventManager().callEvent(
                new PlayerJoin(event)
        );
    }
}
