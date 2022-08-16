package dev.mruniverse.slimelib.events.event;

import dev.mruniverse.slimelib.events.listener.EventListener;

import java.lang.reflect.Method;

public class RegisteredEvent {

    private final EventListener listener;
    private final Method method;

    public RegisteredEvent(EventListener listener, Method method) {
        this.listener = listener;
        this.method = method;
    }

    public EventListener getListener() {
        return listener;
    }

    public Method getMethod() {
        return method;
    }

    public boolean match(Object event) {
        if (method.getParameters().length != 1) {
            return false;
        }
        return method.getParameterTypes()[0] == event.getClass();
    }
}
