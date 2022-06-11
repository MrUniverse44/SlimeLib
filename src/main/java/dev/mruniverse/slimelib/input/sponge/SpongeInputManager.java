package dev.mruniverse.slimelib.input.sponge;

import dev.mruniverse.slimelib.input.InputManager;

import java.io.InputStream;

public class SpongeInputManager implements InputManager {

    @Override
    public InputStream getInputStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }
}