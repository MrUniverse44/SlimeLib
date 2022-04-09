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
     * @param platform - Use a different resource file depending on the platform
     * @return String
     */
    String getResourceFileName(SlimePlatform platform);

    boolean isInDifferentFolder();
}
