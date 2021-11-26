package dev.mruniverse.guardianstorageapi.interfaces;

import com.sun.istack.internal.Nullable;

import java.io.File;
import java.util.List;
import java.util.Random;

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

    Object get(String path);

    Object get(String path, @Nullable Object def);

    void save();

    int getInt(String path,int def);

    int getInt(String path);

    boolean contains(String path);

    boolean getStatus(String path);

    boolean getStatus(String path,boolean def);

    void set(String path,Object value);
}
