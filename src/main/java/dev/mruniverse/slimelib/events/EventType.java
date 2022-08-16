package dev.mruniverse.slimelib.events;

import dev.mruniverse.slimelib.events.events.*;

public enum EventType {
    PLAYER_COMMAND_EVENT(PlayerCommandEvent.class),
    PLAYER_SWITCH_EVENT(PlayerSwitchEvent.class),
    PLAYER_LOGIN_EVENT(PlayerLoginEvent.class),
    SERVER_LIST_EVENT(ServerPingEvent.class),
    PLAYER_JOIN_EVENT(PlayerJoinEvent.class),
    PLAYER_QUIT_EVENT(PlayerQuitEvent.class),
    NONE(EventType.class);

    private final Class<?> event;

    EventType(Class<?> event) {
        this.event = event;
    }

    public Class<?> getEventClass() {
        return event;
    }

}
