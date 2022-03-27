package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimePlugin;

import dev.mruniverse.slimelib.listeners.events.DefaultSlimeListener;
import dev.mruniverse.slimelib.listeners.events.SlimeEvent;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public abstract class BaseSlimeListenerManager<T> {
    private final SlimePlugin<T> plugin;

    private final Map<SlimeEvent.Group, List<DefaultSlimeListener>> listenerMap = new EnumMap<>(SlimeEvent.Group.class);

    public BaseSlimeListenerManager(SlimePlugin<T> plugin) {
        this.plugin = plugin;
    }

    /**
     * This will call a slime event
     */
    public void callSlimeEvent(SlimeEvent event) {
        List<DefaultSlimeListener> listenerList = listenerMap.get(event.getGroup());

        if (listenerList == null) {
            return;
        }

        for (DefaultSlimeListener listener : listenerList) {
            call(listener, event);
        }

    }

    private void call(DefaultSlimeListener listener, SlimeEvent event) {

    }

    public void registerSlimeEvent() {

    }

    public void reload() {

    }
}

