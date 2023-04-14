package me.blueslime.slimelib.file.input;

import java.io.InputStream;

public class DefaultInputManager {
    private static DefaultInputManager INSTANCE = null;

    public InputStream getInputStream(String resource) {
        return getClass().getResourceAsStream(resource);
    }

    public static DefaultInputManager init() {
        if (INSTANCE == null) {
            INSTANCE = new DefaultInputManager();
        }
        return INSTANCE;
    }
}