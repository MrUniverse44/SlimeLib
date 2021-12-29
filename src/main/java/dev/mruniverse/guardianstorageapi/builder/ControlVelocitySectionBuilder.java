package dev.mruniverse.guardianstorageapi.builder;


import dev.mruniverse.guardianstorageapi.interfaces.Control;
import dev.mruniverse.guardianstorageapi.interfaces.GLogger;
import dev.mruniverse.guardianstorageapi.utils.Configuration;
import dev.mruniverse.guardianstorageapi.utils.ConfigurationProvider;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ControlVelocitySectionBuilder implements Control {

    private final Configuration configuration;

    private final File file;

    private final GLogger logs;

    private Configuration fileConfig;

    public ControlVelocitySectionBuilder(File file,GLogger logs,Configuration fileConfig,Configuration section) {
        this.configuration = section;
        this.logs = logs;
        this.fileConfig = fileConfig;
        this.file = file;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public List<String> getColoredStringList(String path) {
        List<String> coloredList = new ArrayList<>();
        configuration.getStringList(path).forEach(text -> coloredList.add(LegacyComponentSerializer.builder().character('&').build().deserialize(text).content()));
        return coloredList;
    }

    @Override
    public String getColoredString(String path) {
        String message = configuration.getString(path,"&cInvalid path: &e" + path);
        return LegacyComponentSerializer.builder().character('&').build().deserialize(message).content();
    }

    @Override
    public String getColoredString(String path,String def) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(configuration.getString(path,def)).content();
    }

    @Override
    public String getStringWithoutColors(String path) {
        return configuration.getString(path,"Invalid path: " + path);
    }

    @Override
    public String getStringWithoutColors(String path,String def) {
        return configuration.getString(path,def);
    }

    @Override
    public void save() {
        try {
            ConfigurationProvider.getProvider(ConfigurationProvider.Provider.YAML).save(this.fileConfig, this.file);
        }catch (Throwable throwable) {
            logs.error("Can't save file: " + file.getName());
            logs.error(throwable);
        }
    }

    @Override
    public void reload() {
        try {
            fileConfig = ConfigurationProvider.getProvider(ConfigurationProvider.Provider.YAML).load(file);
        }catch (Throwable throwable) {
            logs.error("Can't reload file: " + file.getName());
            logs.error(throwable);
        }
    }

    @Override
    public List<?> getList(String path) {
        return configuration.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return configuration.getList(path,def);
    }

    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    @Override
    public List<String> getContent(String path, boolean getKeys) {
        List<String> rx = new ArrayList<>();
        Configuration section = configuration.getSection(path);
        if(section == null) return rx;
        rx.addAll(section.getKeys());
        return rx;
    }

    @Override
    public List<Integer> getIntList(String path) {
        return configuration.getIntList(path);
    }

    @Override
    public int getInt(String path, int def) {
        return configuration.getInt(path,def);
    }

    @Override
    public int getInt(String path) {
        return configuration.getInt(path);
    }

    @Override
    public boolean contains(String path) {
        return configuration.contains(path);
    }

    @Override
    public boolean getStatus(String path) {
        return configuration.getBoolean(path);
    }

    @Override
    public Object get(String path) { return configuration.get(path); }

    @Override
    public Object get(String path, Object def) { return configuration.get(path,def); }

    @Override
    public long getLong(String path,long def) { return configuration.getLong(path,def); }

    @Override
    public Control getSection(String path) {
        return new ControlVelocitySectionBuilder(file,logs,fileConfig,configuration.getSection(path));
    }

    @Override
    public long getLong(String path) { return configuration.getLong(path); }

    @Override
    public List<Long> getLongList(String path) { return configuration.getLongList(path); }

    @Override
    public List<Boolean> getBooleanList(String path) { return configuration.getBooleanList(path); }

    @Override
    public List<Byte> getByteList(String path) { return configuration.getByteList(path); }

    @Override
    public List<Character> getCharList(String path) { return configuration.getCharList(path); }
    @Override
    public List<Float> getFloatList(String path) { return configuration.getFloatList(path); }

    @Override
    public boolean getStatus(String path, boolean def) {
        return configuration.getBoolean(path,def);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path,value);
    }

    @Override
    public String getString(String path, String def) {
        return configuration.getString(path,def);
    }

    @Override
    public String getString(String path) {
        return configuration.getString(path,"&cInvalid Path: &e" + path);
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return (Set<String>) configuration.getKeys();
    }

}

