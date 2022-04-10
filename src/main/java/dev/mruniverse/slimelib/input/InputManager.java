package dev.mruniverse.slimelib.input;

import java.io.InputStream;

public interface InputManager {

    /**
     * Get a InputStream from a resource file.
     * @param resource File Path with File Name
     * @return InputStream
     */
    InputStream getInputStream(String resource);
}
