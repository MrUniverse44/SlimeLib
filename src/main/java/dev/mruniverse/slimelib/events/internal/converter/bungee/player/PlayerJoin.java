package dev.mruniverse.slimelib.events.internal.converter.bungee.player;

import dev.mruniverse.slimelib.events.events.PlayerJoinEvent;
import dev.mruniverse.slimelib.source.SlimeSource;
import dev.mruniverse.slimelib.source.player.SlimeProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;

public class PlayerJoin extends PlayerJoinEvent {

    private final ServerConnectEvent event;
    private final SlimeSource<?> source;

    public PlayerJoin(ServerConnectEvent event) {
        this.source = new SlimeProxiedPlayer(
                event.getPlayer()
        );
        this.event  = event;
    }

    @Override
    public String getJoinMessage() {
        return null;
    }

    @Override
    public void setJoinMessage(String message) {
        //TODO: This will not be worked because is bungee and bungee don't have join location
    }

    @Override
    public SlimeSource<?> getSource() {
        return source;
    }
}
