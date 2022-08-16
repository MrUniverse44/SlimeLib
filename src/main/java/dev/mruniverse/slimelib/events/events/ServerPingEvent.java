package dev.mruniverse.slimelib.events.events;

import dev.mruniverse.slimelib.events.event.Event;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

public abstract class ServerPingEvent extends Event {

    public abstract InetAddress getAddress();

    public abstract String getMotd();

    public abstract void setMotd(@NotNull String motd);

    public abstract int getOnlinePlayers();

    public abstract int getMaxPlayers();

    public abstract void setOnlinePlayers(int online);

    public abstract void setMaxPlayers(int max);
}
