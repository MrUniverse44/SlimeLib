package dev.mruniverse.slimelib.file.input.sponge;

import dev.mruniverse.slimelib.file.input.InputManager;

import java.io.InputStream;

public class SpongeInputManager implements InputManager {

    @Override
    public InputStream getInputStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }
}