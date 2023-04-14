package me.blueslime.slimelib;

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
     * The name of the resource file
     * @param platform - Use a different resource file depending on the platform
     * @return String - File Name
     */
    String getResourceFileName(SlimePlatform platform);

    /**
     * This will load the file in a specific platform.
     * @param platform Platform
     * @return Boolean, true by default.
     */
    @SuppressWarnings("unused")
    default boolean loadOnPlatform(SlimePlatform platform) {
        return true;
    }

    boolean isInDifferentFolder();
}
