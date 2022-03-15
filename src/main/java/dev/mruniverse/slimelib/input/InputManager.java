package dev.mruniverse.slimelib.input;

import java.io.InputStream;

public interface InputManager {
    InputStream getInputStream(String resource);

    boolean isBungee();
}
