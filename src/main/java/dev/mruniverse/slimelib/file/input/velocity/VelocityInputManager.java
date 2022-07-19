package dev.mruniverse.slimelib.file.input.velocity;

import dev.mruniverse.slimelib.file.input.InputManager;

import java.io.InputStream;

public class VelocityInputManager implements InputManager {

    @Override
    public InputStream getInputStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }
}
