package dev.mruniverse.slimelib.input.velocity;

import dev.mruniverse.slimelib.input.InputManager;

import java.io.InputStream;

public class VelocityInputManager implements InputManager {
    private final Class<?> plugin;

    public <T> VelocityInputManager(T plugin) {
        this.plugin = (Class<?>) plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getResourceAsStream(resource);
    }
}
