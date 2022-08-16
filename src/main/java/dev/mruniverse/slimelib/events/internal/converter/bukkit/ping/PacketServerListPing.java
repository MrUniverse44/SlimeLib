package dev.mruniverse.slimelib.events.internal.converter.bukkit.ping;

import dev.mruniverse.slimelib.events.events.ServerPingEvent;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

public class PacketServerListPing extends ServerPingEvent {
    @Override
    public InetAddress getAddress() {
        return null;
    }

    @Override
    public String getMotd() {
        return null;
    }

    @Override
    public void setMotd(@NotNull String motd) {

    }

    @Override
    public int getOnlinePlayers() {
        return 0;
    }

    @Override
    public int getMaxPlayers() {
        return 0;
    }

    @Override
    public void setOnlinePlayers(int online) {

    }

    @Override
    public void setMaxPlayers(int max) {

    }
}
