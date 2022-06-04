package dev.mruniverse.slimelib.exceptions;

public class SlimeControlLoadException extends Exception {

    public SlimeControlLoadException(Exception e) {
        super("The Slime Control can't reload files, by the following exception: " + e.getClass().getSimpleName());
        super.addSuppressed(e);
    }

}
