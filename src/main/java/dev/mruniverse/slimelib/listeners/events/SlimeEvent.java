package dev.mruniverse.slimelib.listeners.events;

@SuppressWarnings("unused")
public class SlimeEvent {

    private final Group group;

    private final String name;

    public SlimeEvent(final Group group, final String name) {
        error(group == null, "The group of this event can't be null");
        error(group == Group.CUSTOM, "Don't use this syntax to create custom events, try with SlimeEvent(String)");
        this.group = group;
        this.name = name;
    }

    public SlimeEvent(final String name) {
        this.group = Group.CUSTOM;
        this.name = name;
    }

    private void error(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Get the group of the event
     * @return Group that this event represents
     */
    public final Group getGroup() {
        return group;
    }

    /**
     * Gets the name of the event.
     * @return Event Name
     */
    public final String getName() {
        return name;
    }

    public enum Group {
        /**
         * Represents when a player join to the server
         */
        PLAYER_JOIN,

        /**
         * Represents when a player quit from the server
         */
        PLAYER_QUIT,

        /**
         * Represents when a player is connecting to the server
         */
        PLAYER_LOGIN,

        /**
         * Represents when a player chat in to the server
         */
        PLAYER_CHAT,

        /**
         * Represents when a player uses a command
         */
        PLAYER_COMMAND,

        /**
         * Represents when a player switch from a world or server
         */
        PLAYER_SWITCH,

        /**
         * Represents when someone is pinning the server
         */
        PING,

        /**
         * Represents a custom event
         */
        CUSTOM
    }



}
