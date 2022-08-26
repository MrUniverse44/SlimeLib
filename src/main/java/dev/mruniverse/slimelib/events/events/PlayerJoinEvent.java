package dev.mruniverse.slimelib.events.events;

import dev.mruniverse.slimelib.events.event.Event;
import dev.mruniverse.slimelib.source.SlimeSource;

public abstract class PlayerJoinEvent extends Event {


    public abstract String getJoinMessage();

    public abstract void setJoinMessage(String message);

    public abstract SlimeSource<?> getSource();

}
