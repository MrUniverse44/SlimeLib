package dev.mruniverse.slimelib.events.internal.listeners;

import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventCaller;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.converter.bukkit.player.PlayerJoin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitListener extends EventCaller<JavaPlugin> implements Listener {

    public BukkitListener(EventExecutor<JavaPlugin> manager) {
        super(manager);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        boolean status = getManager().getEventRegistered().get(EventType.PLAYER_JOIN_EVENT);

        if (!status) {
            return;
        }

        PlayerJoin customEvent = new PlayerJoin(event);

        getManager().getEventManager().callEvent(
                customEvent
        );
    }
}
