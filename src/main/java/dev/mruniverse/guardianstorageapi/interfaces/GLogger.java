package dev.mruniverse.guardianstorageapi.interfaces;

public interface GLogger {
    String color(String message);

    void error(String message);

    void error(Throwable throwable);

    void warn(String message);

    void debug(String message);

    void info(String message);

    void sendMessage(String message);

    GLogger setErrorPrefix(String errorPrefix);

    GLogger setDebugPrefix(String debugPrefix);

    GLogger setInfoPrefix(String infoPrefix);

    GLogger setWarnPrefix(String warnPrefix);
}
