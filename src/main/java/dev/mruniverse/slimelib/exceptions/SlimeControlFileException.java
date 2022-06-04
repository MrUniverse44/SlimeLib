package dev.mruniverse.slimelib.exceptions;

public class SlimeControlFileException extends Exception {

    public SlimeControlFileException(Exception e) {
        super("The Slime Control can't control files, by the following exception: " + e.getClass().getSimpleName());
        super.addSuppressed(e);
    }

}
