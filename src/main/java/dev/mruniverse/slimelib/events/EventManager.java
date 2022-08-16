package dev.mruniverse.slimelib.events;

import dev.mruniverse.slimelib.events.event.Event;
import dev.mruniverse.slimelib.events.event.Events;
import dev.mruniverse.slimelib.events.event.RegisteredEvent;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.listener.EventListener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventManager<T> {
    private final List<RegisteredEvent> registeredEvents = new ArrayList<>();

    private final EventExecutor<T> executor;

    public EventManager(EventExecutor<T> executor) {
        this.executor = executor;
        this.executor.setManager(this);
    }

    public void registerListener(Object plugin, EventListener listener) {
        for (Method method : listener.getClass().getMethods()) {
            if (method.isAnnotationPresent(Events.class)) {
                executor.add(
                    plugin,
                    listener,
                    matchForType(
                            method.getParameters()[0].getClass()
                    )
                );
                registeredEvents.add(
                        new RegisteredEvent(
                                listener,
                                method
                        )
                );
            }
        }
    }

    private EventType matchForType(Class<?> clazz) {
        for (EventType eventClass : EventType.values()) {
            if (eventClass == EventType.NONE) {
                continue;
            }
            if (eventClass.getEventClass() == clazz) {
                return eventClass;
            }
        }
        return EventType.NONE;
    }

    public void unregisterListener(EventListener listener) {

        ArrayList<RegisteredEvent> events = new ArrayList<>(registeredEvents);

        for (RegisteredEvent registeredEvent : events) {
            if (registeredEvent.getListener().equals(listener)) {
                registeredEvents.remove(registeredEvent);
            }
        }

    }

    public void unregisterAll() {
        registeredEvents.clear();
    }

    public void callEvent(Event event) {
        for (RegisteredEvent registeredEvent : registeredEvents) {
            if (registeredEvent.match(event)) {
                try {
                    registeredEvent.getMethod().invoke(
                            registeredEvent.getListener(),
                            event
                    );
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
