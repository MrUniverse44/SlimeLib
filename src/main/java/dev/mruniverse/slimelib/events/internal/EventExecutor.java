package dev.mruniverse.slimelib.events.internal;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.events.EventManager;
import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.platform.BungeeEventExecutor;
import dev.mruniverse.slimelib.events.internal.platform.SpigotEventExecutor;
import dev.mruniverse.slimelib.events.internal.platform.SpongeEventExecutor;
import dev.mruniverse.slimelib.events.internal.platform.VelocityEventExecutor;
import dev.mruniverse.slimelib.events.listener.EventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class EventExecutor<T> {
    private final ConcurrentHashMap<EventType, List<EventListener>> eventMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<EventType, Boolean> eventRegistered = new ConcurrentHashMap<>();

    private EventManager<T> eventManager = null;

    private final T plugin;

    public EventExecutor(T plugin) {
        this.plugin = plugin;
    }

    public void setManager(EventManager<T> eventManager) {
        this.eventManager = eventManager;
    }

    public void add(Object plugin, EventListener listener, EventType type) {
        List<EventListener> eventList = eventMap.computeIfAbsent(
                type, F -> new LinkedList<>()
        );

        boolean status = eventRegistered.computeIfAbsent(
                type,
                F -> false
        );

        if (eventList.isEmpty() && !status) {
            register(plugin, type);
        }

        eventList.add(listener);
    }

    public abstract void register(Object plugin, EventType type);

    public T getPlugin() {
        return plugin;
    }

    public static <T> EventExecutor<?> fromType(SlimePlatform platform, T plugin) {
        switch (platform) {
            case SPIGOT:
                return new SpigotEventExecutor(plugin);
            case SPONGE:
                return new SpongeEventExecutor(plugin);
            case VELOCITY:
                return new VelocityEventExecutor(plugin);
            default:
            case BUNGEECORD:
                return new BungeeEventExecutor(plugin);
        }
    }

    public EventManager<T> getEventManager() {
        return eventManager;
    }

    public ConcurrentHashMap<EventType, Boolean> getEventRegistered() {
        return eventRegistered;
    }

    public ConcurrentHashMap<EventType, List<EventListener>> getEventMap() {
        return eventMap;
    }
}
