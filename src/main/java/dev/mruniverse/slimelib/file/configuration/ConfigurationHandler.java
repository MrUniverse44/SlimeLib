package dev.mruniverse.slimelib.file.configuration;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.colors.SlimeColor;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("unused")
public abstract class ConfigurationHandler {

    private final Random RANDOM = new Random();

    private final InputStream resource;

    private final SlimeLogs logs;

    private final File file;

    public ConfigurationHandler(SlimeLogs logs, File file, InputStream resource) {
        this.file = file;
        this.logs = logs;
        this.resource = resource;
        load();
    }

    public ConfigurationHandler(SlimeLogs logs, File file) {
        this.file = file;
        this.logs = logs;
        this.resource = null;
        load();
    }

    public abstract void load();

    public static ConfigurationHandler loadControl(SlimeLogs logs, File file) {
        return loadControl(
                SlimePlatform.getAutomatically(),
                logs,
                file
        );
    }

    public static ConfigurationHandler loadControl(SlimePlatform platform, SlimeLogs logs, File file) {
        return platform.getProvider().getNewInstance().create(logs, file);
    }

    public abstract <T> T toSpecifiedConfiguration();


    /**
     * Gives a list
     * @param path Path of the list
     * @return List
     */
    public abstract List<?> getList(String path);

    /**
     * Gives a list
     * @param path Path of the list
     * @param def Default List
     * @return List. If path-list exists gives the list of the path
     * If the path list doesn't exist, gives the default list
     */
    public abstract List<?> getList(String path, List<?> def);

    /**
     * Creates a SlimeColor from a String
     * @param path Path Location
     * @param def Default String
     * @return SlimeColor
     */
    public SlimeColor getSlimeColor(String path, String def) {
        return getSlimeColor(
                TextDecoration.ALL,
                path,
                def
        );
    }

    /**
     * Creates a SlimeColor from a String
     * @param path Path Location
     * @return SlimeColor
     */
    public SlimeColor getSlimeColor(String path) {
        return getSlimeColor(
                TextDecoration.ALL,
                path
        );
    }

    /**
     * Creates a SlimeColor from a String
     * @param decoration TextDecoration
     * @param path Path Location
     * @return SlimeColor
     */
    public SlimeColor getSlimeColor(TextDecoration decoration, String path) {
        return getSlimeColor(decoration, path, "&cInvalid path: &e" + path);
    }

    /**
     * Creates a SlimeColor from a String
     * @param decoration TextDecoration
     * @param path Path Location
     * @param def Default String
     * @return SlimeColor
     */
    public SlimeColor getSlimeColor(TextDecoration decoration, String path, String def) {
        return new SlimeColor(
                SlimePlatform.getAutomatically(),
                getString(path, def),
                decoration.toColorMethod()
        );
    }

    /**
     * Creates a SlimeColor from a String
     * @param path Path Location
     * @param def Default Text
     * @return SlimeColor
     */
    public List<SlimeColor> getSlimeColorList(String path, String def) {
        return getSlimeColorList(TextDecoration.ALL, path);
    }

    /**
     * Creates a SlimeColor from a String
     * @param decoration TextDecoration
     * @param path Path Location
     * @return SlimeColor
     */
    public List<SlimeColor> getSlimeColorList(TextDecoration decoration, String path) {

        SlimePlatform platform = SlimePlatform.getAutomatically();

        List<String> stringList = getStringList(path);

        List<SlimeColor> colorList = new ArrayList<>();

        stringList.forEach(line -> colorList.add(
                new SlimeColor(
                        platform,
                        line,
                        decoration.toColorMethod()
                )
        ));

        return colorList;
    }

    /**
     * Gives a String-Text
     * @param path String Path Location
     * @return Colored String
     */
    public String getString(String path) {
        return getString(TextDecoration.NONE, path);
    }

    /**
     * Gives a String-Text
     * @param path String Path Location
     * @param def If the path doesn't exist, this will be the default result
     * @return Colored String
     */
    public String getString(String path, String def) {
        return getString(TextDecoration.NONE, path, def);
    }

    /**
     * Gives a String-Text
     * @param path String Path Location
     * @return Colored String
     */
    public abstract String getString(TextDecoration decoration, String path);

    /**
     * Gives a String-Text
     * @param path String Path Location
     * @param def If the path doesn't exist, this will be the default result
     * @return Colored String
     */
    public abstract String getString(TextDecoration decoration, String path, String def);

    /**
     * Gives a StringList
     * @param path Path of the list
     * @return StringList
     */
    public List<String> getStringList(String path) {
        return getStringList(TextDecoration.NONE, path);
    }

    /**
     * Gives a StringList
     * @param path Path of the list
     * @return StringList
     */
    public abstract List<String> getStringList(TextDecoration decoration, String path);

    /**
     * Gives an Integer List
     * @param path Integer List Location Path
     * @return Integer List
     */
    public abstract List<Integer> getIntList(String path);

    public abstract void reload();

    public abstract long getLong(String path, long def);

    public abstract long getLong(String path);

    public abstract List<Long> getLongList(String path);

    public abstract List<Boolean> getBooleanList(String path);

    public abstract List<Byte> getByteList(String path);

    public abstract List<Character> getCharList(String path);

    public abstract List<Float> getFloatList(String path);

    public abstract Object get(String path);

    public abstract Object get(String path, Object def);

    public abstract void save();

    public abstract int getInt(String path, int def);

    public abstract int getInt(String path);

    public abstract boolean contains(String path);

    /**
     * Check if the path exists, if the path doesn't exist this will return
     * false, but if the path exists it will return true.
     * @param path Path Location
     * @return Boolean
     */
    public boolean getBoolean(String path) {
        return getStatus(path);
    }

    /**
     * Check if the path exists, if the path doesn't exist this will return
     * the default boolean specified, but if the path exists it will return true.
     * @param path Path Location
     * @param def Default Result if the path doesn't exist
     * @return Boolean
     */
    public boolean getBoolean(String path, boolean def) {
        return getStatus(path, def);
    }

    /**
     * Check if the path exists, if the path doesn't exist this will return
     * false, but if the path exists it will return true.
     * @param path Path Location
     * @return Boolean
     */
    public abstract boolean getStatus(String path);

    /**
     * Check if the path exists, if the path doesn't exist this will return
     * the default boolean specified, but if the path exists it will return true.
     * @param path Path Location
     * @param def Default Result if the path doesn't exist
     * @return Boolean
     */
    public abstract boolean getStatus(String path, boolean def);

    /**
     * Set a path to a specified result
     * @param path Path Location
     * @param value Value of the path
     */
    public abstract void set(String path,Object value);

    /**
     * Get keys of a specified path
     * @param path Path of the content
     * @param getKeys get the content with keys
     * @return StringList
     */
    public abstract List<String> getContent(String path, boolean getKeys);

    /**
     * Get a configuration handler section
     * @param path Section location
     * @return ConfigurationHandler
     */
    public abstract ConfigurationHandler getSection(String path);

    /**
     * Create a new Configuration handler section
     * @param path Section location
     * @return ConfigurationHandler
     */
    public abstract ConfigurationHandler createSection(String path);

    public abstract Set<String> getKeys(boolean deep);

    public Random getRandom() {
        return RANDOM;
    }

    public InputStream getResource() {
        return resource;
    }

    public SlimeLogs getLogs() {
        return logs;
    }

    public File getFile() {
        return file;
    }
}
