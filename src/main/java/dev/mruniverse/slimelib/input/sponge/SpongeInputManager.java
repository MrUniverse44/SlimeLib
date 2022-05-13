package dev.mruniverse.slimelib.input.sponge;

import dev.mruniverse.slimelib.input.InputManager;

import java.io.InputStream;

public class SpongeInputManager implements InputManager {
    private final Class<?> plugin;

    public <T> SpongeInputManager(T plugin) {
        this.plugin = (Class<?>) plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getResourceAsStream(resource);
    }
}