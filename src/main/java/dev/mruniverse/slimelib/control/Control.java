package dev.mruniverse.slimelib.control;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.storage.provider.ControlBungeeProvider;
import dev.mruniverse.slimelib.storage.provider.ControlSpigotProvider;
import dev.mruniverse.slimelib.storage.provider.DefaultControlProvider;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("unused")
public interface Control {

    Random RANDOM = new Random();

    default Random getRandom() {
        return RANDOM;
    }

    default Control loadControl(SlimeLogs logs, File file) {
        return loadControl(
                SlimePlatform.getAutomatically(),
                logs,
                file
        );
    }

    default Control loadControl(SlimePlatform platform, SlimeLogs logs, File file) {
        switch (platform) {
            case SPIGOT:
                return new ControlSpigotProvider().create(logs, file);
            case BUNGEECORD:
                return new ControlBungeeProvider().create(logs, file);
            default:
            case VELOCITY:
            case SPONGE:
                return new DefaultControlProvider().create(logs, file);
        }
    }

    /**
     * Gives a list
     * @param path Path of the list
     * @return List
     */
    List<?> getList(String path);

    /**
     * Gives a list
     * @param path Path of the list
     * @param def Default List
     * @return List. If path-list exists gives the list of the path
     * If the path list doesn't exist, gives the default list
     */
    List<?> getList(String path,List<?> def);

    /**
     * Gives a StringList
     * @param path Path of the list
     * @return StringList
     */
    List<String> getStringList(String path);

    /**
     * Gives a colored StringList
     * @param path Path of the list
     * @return StringList
     */
    List<String> getColoredStringList(String path);

    /**
     * Literally is similar {@link net.md_5.bungee.config.Configuration#getKeys()} or
     * {@link org.bukkit.configuration.file.FileConfiguration#getKeys(boolean)}
     * @param path Path of the content
     * @param getKeys get the content with keys
     * @return StringList
     */
    List<String> getContent(String path, boolean getKeys);

    /**
     * Gives an Integer List
     * @param path Integer List Location Path
     * @return Integer List
     */
    List<Integer> getIntList(String path);

    /**
     * Gives a Colored String-Text
     * @param path String Path Location
     * @return Colored String
     */
    String getColoredString(String path);

    /**
     * Gives a Colored String-Text
     * @param path String Path Location
     * @param def If the path doesn't exist, this will be the default result
     * @return Colored String
     */
    String getColoredString(String path,String def);

    /**
     * Strip colors of a string
     * @param path String Path Location
     * @return String without colors
     */
    String getStringWithoutColors(String path);

    /**
     * Strip colors of a string
     * @param path String Path Location
     * @param def If the path doesn't exist, this will be the default result
     * @return String without colors
     */
    String getStringWithoutColors(String path,String def);

    /**
     * Gives a String
     * @param path String Path Location
     * @param def If the path doesn't exist, this will be the default result
     * @return String
     */
    String getString(String path,String def);

    /**
     * Gives a String
     * @param path String Path Location
     * @return String
     */
    String getString(String path);

    File getFile();

    void reload();

    long getLong(String path, long def);

    long getLong(String path);

    List<Long> getLongList(String path);

    List<Boolean> getBooleanList(String path);

    List<Byte> getByteList(String path);

    List<Character> getCharList(String path);

    List<Float> getFloatList(String path);

    Object get(String path);

    Object get(String path, Object def);

    void save();

    int getInt(String path, int def);

    int getInt(String path);

    boolean contains(String path);

    /**
     * Similar to {@link net.md_5.bungee.config.Configuration#getBoolean(String)}
     * or {@link org.bukkit.configuration.file.FileConfiguration#getBoolean(String)}
     * @param path Path Location
     * @return Boolean
     */
    boolean getStatus(String path);

    /**
     * Similar to {@link net.md_5.bungee.config.Configuration#getBoolean(String)}
     * or {@link org.bukkit.configuration.file.FileConfiguration#getBoolean(String)}
     * @param path Path Location
     * @param def Default Result if the path doesn't exist
     * @return Boolean
     */
    boolean getStatus(String path,boolean def);

    /**
     * Set a path to a specified result
     * @param path Path Location
     * @param value Value of the path
     */
    void set(String path,Object value);

    Set<String> getKeys(boolean deep);

    Control getSection(String path);

    Object getObjectConfiguration();
}
