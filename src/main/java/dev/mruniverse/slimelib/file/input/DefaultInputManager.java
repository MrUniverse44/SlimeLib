package dev.mruniverse.slimelib.file.input;

import java.io.InputStream;

public class DefaultInputManager implements InputManager {
    @Override
    public InputStream getInputStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }
}
