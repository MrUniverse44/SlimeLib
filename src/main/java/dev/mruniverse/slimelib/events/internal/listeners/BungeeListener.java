package dev.mruniverse.slimelib.events.internal.listeners;

import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventCaller;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.converter.bungee.player.PlayerJoin;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeListener extends EventCaller<Plugin> implements Listener {
    public BungeeListener(EventExecutor<Plugin> manager) {
        super(manager);
    }

    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        boolean status = getManager().getEventRegistered().get(EventType.PLAYER_SWITCH_EVENT);

        if (!status) {
            return;
        }

        getManager().getEventManager().callEvent(
                new PlayerJoin(event)
        );
    }
}
