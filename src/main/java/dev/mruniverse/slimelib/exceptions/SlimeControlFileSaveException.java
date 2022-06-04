package dev.mruniverse.slimelib.exceptions;

public class SlimeControlFileSaveException extends Exception {

    public SlimeControlFileSaveException(Exception e) {
        super("The Slime Control can't save your files, by the following exception: " + e.getClass().getSimpleName());
        super.addSuppressed(e);
    }

}
