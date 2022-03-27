package dev.mruniverse.slimelib.listeners;

/**
 * Represents an Slime Event Priority in execution
 */
public enum SlimeEventPriority {

    /**
     * This priority is a call when is of very low importance and should be run first
     */
    LOWEST,
    /**
     * This priority is a call when is of low importance
     */
    LOW,
    /**
     * This priority is a call when the priority will be by default
     */
    NORMAL,
    /**
     * This priority is a call when the event is important for the server
     */
    HIGH,
    /**
     * This priority is a call when the event is more important event for the server
     */
    HIGHEST
}

