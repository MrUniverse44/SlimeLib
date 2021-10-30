package dev.mruniverse.guardianstorageapi.interfaces;

import java.io.InputStream;

public interface InputManager {
    InputStream getInputStream(String resource);

    boolean isBungee();
}
