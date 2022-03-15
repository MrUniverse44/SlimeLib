package dev.mruniverse.slimelib;

public interface SlimeFiles {

    /**
     * return the name of the file
     * @return String
     */
    String getFileName();

    /**
     * return the name of the folder
     * @return String
     */
    String getFolderName();

    /**
     * return the name of the resource file
     * @return String
     */
    String getResourceFileName();

    boolean isInDifferentFolder();
}
