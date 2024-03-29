package me.blueslime.slimelib.logs.platforms.sponge;

import me.blueslime.slimelib.logs.SlimeLogger;

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

    public SlimeLoggerSponge getNewInstance(String pluginName) {
        SlimeLogger logger = new SlimeLogger();
        logger.setPluginName(pluginName);
        return new SlimeLoggerSponge(
                logger
        );
    }

}
