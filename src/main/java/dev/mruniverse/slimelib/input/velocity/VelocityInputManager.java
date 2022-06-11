package dev.mruniverse.slimelib.input.velocity;

import dev.mruniverse.slimelib.input.InputManager;

import java.io.InputStream;

public class VelocityInputManager implements InputManager {

    @Override
    public InputStream getInputStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }
}
