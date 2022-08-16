package dev.mruniverse.slimelib.events.internal;

import dev.mruniverse.slimelib.events.EventManager;

public abstract class EventCaller<T> {

    private final EventExecutor<T> manager;

    public EventCaller(EventExecutor<T> manager) {
        this.manager = manager;
    }

    public EventExecutor<T> getManager() {
        return manager;
    }
}
