package dev.mruniverse.slimelib.logs.platforms.sponge;

public class LoggerSponge {

    /**
     * This logger doesn't need instance.
     */
    public LoggerSponge() {
        // NOT NEED
    }

    public SlimeLoggerSponge getNewInstance() {
        return new SlimeLoggerSponge();
    }

}
