package dev.mruniverse.slimelib.events.internal.converter.bungee.ping;

import dev.mruniverse.slimelib.events.events.ServerPingEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;

public class ProxyPing extends ServerPingEvent {

    private final ProxyPingEvent event;

    public ProxyPing(ProxyPingEvent event) {
        this.event = event;
    }

    @Override
    public InetAddress getAddress() {
        return event.getConnection().getAddress().getAddress();
    }

    @Override
    public String getMotd() {
        return event.getResponse().getDescriptionComponent().toLegacyText();
    }

    @Override
    public void setMotd(@NotNull String motd) {
        event.getResponse().setDescriptionComponent(
                new TextComponent(motd)
        );
    }

    @Override
    public int getOnlinePlayers() {
        return event.getResponse().getPlayers().getOnline();
    }

    @Override
    public int getMaxPlayers() {
        return event.getResponse().getPlayers().getMax();
    }

    @Override
    public void setOnlinePlayers(int online) {
        event.getResponse().getPlayers().setOnline(online);
    }

    @Override
    public void setMaxPlayers(int max) {
        event.getResponse().getPlayers().setMax(max);
    }
}