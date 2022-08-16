package dev.mruniverse.slimelib.events.internal.converter.bukkit.ping;

import dev.mruniverse.slimelib.events.events.ServerPingEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

public class NormalServerListPing extends ServerPingEvent {

    private final ServerListPingEvent event;

    public NormalServerListPing(ServerListPingEvent event) {
        this.event = event;
    }

    @Override
    public InetAddress getAddress() {
        return event.getAddress();
    }

    @Override
    public String getMotd() {
        return event.getMotd();
    }

    @Override
    public void setMotd(@NotNull String motd) {
        event.setMotd(motd);
    }

    @Override
    public int getOnlinePlayers() {
        return event.getNumPlayers();
    }

    @Override
    public int getMaxPlayers() {
        return event.getMaxPlayers();
    }

    @Override
    public void setOnlinePlayers(int online) {
        //TODO: Not Possible on this normal ping.
    }

    @Override
    public void setMaxPlayers(int max) {
        event.setMaxPlayers(max);
    }
}
