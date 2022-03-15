package dev.mruniverse.slimelib.control;

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

    List<?> getList(String path);

    List<?> getList(String path,List<?> def);

    List<String> getStringList(String path);

    List<String> getColoredStringList(String path);

    List<String> getContent(String path, boolean getKeys);

    List<Integer> getIntList(String path);

    String getColoredString(String path);

    String getColoredString(String path,String def);

    String getStringWithoutColors(String path);

    String getStringWithoutColors(String path,String def);

    String getString(String path,String def);

    String getString(String path);

    File getFile();

    void reload();

    long getLong(String path,long def);

    long getLong(String path);

    List<Long> getLongList(String path);

    List<Boolean> getBooleanList(String path);

    List<Byte> getByteList(String path);

    List<Character> getCharList(String path);

    List<Float> getFloatList(String path);

    Object get(String path);

    Object get(String path,Object def);

    void save();

    int getInt(String path,int def);

    int getInt(String path);

    boolean contains(String path);

    boolean getStatus(String path);

    boolean getStatus(String path,boolean def);

    void set(String path,Object value);

    Set<String> getKeys(boolean deep);

    Control getSection(String path);
}
