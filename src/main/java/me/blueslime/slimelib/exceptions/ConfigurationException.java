package me.blueslime.slimelib.exceptions;

public class ConfigurationException extends Exception {

    public ConfigurationException(Exception e) {
        super("The Slime Control can't control files, by the following exception: " + e.getClass().getSimpleName());
        super.addSuppressed(e);
    }

}
