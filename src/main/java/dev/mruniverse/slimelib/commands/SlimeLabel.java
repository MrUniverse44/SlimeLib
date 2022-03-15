package dev.mruniverse.slimelib.commands;

public interface SlimeLabel {

    /**
     * @param string the value
     * @return a new slime label
     */
    static SlimeLabel from(String string) {
        return () -> string;
    }

    /**
     * @param object the object
     * @return a new slime label
     */
    static SlimeLabel from(Object object) {
        return object::toString;
    }

    /**
     * The String result from a Slime Label.
     * @return the string value
     */
    String getLabel();

}
