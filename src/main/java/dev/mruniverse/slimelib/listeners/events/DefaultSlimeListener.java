package dev.mruniverse.slimelib.listeners.events;

import dev.mruniverse.slimelib.listeners.SlimeEventPriority;
import dev.mruniverse.slimelib.listeners.SlimeListener;

public class DefaultSlimeListener {

    private final SlimeEventPriority priority;

    private final SlimeListener listener;

    public DefaultSlimeListener(final SlimeListener listener, final SlimeEventPriority priority) {
        this.listener = listener;
        this.priority = priority;
    }

    public SlimeListener getListener() {
        return listener;
    }

    public SlimeEventPriority getPriority() {
        return priority;
    }
}
