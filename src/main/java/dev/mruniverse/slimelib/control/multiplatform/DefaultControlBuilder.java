package dev.mruniverse.slimelib.control.multiplatform;

import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.utils.configuration.PluginConfiguration;

import dev.mruniverse.slimelib.utils.configuration.YamlConfiguration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dev.mruniverse.slimelib.control.Control;

public class DefaultControlBuilder implements Control {
    private final InputStream resource;

    private final SlimeLogs logs;

    private final File file;

    private PluginConfiguration configuration;



    public DefaultControlBuilder(SlimeLogs logs, File file, InputStream resource) {
        this.file = file;
        this.logs = logs;
        this.resource = resource;
        load();
    }

    public DefaultControlBuilder(SlimeLogs logs, File file) {
        this.file = file;
        this.logs = logs;
        this.resource = null;
        load();
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
            YamlConfiguration.save(this.configuration, this.file);
        }catch (Exception exception) {
            logs.error("Can't save file: " + file.getName());
            logs.error(exception);
        }
    }

    @Override
    public void reload() {
        try {
            configuration = YamlConfiguration.load(file);
        }catch (Exception exception) {
            logs.error("Can't reload file: " + file.getName());
            logs.error(exception);
        }
    }

    @Override
    public long getLong(String path, long def) {
        return configuration.getLong(path,def);
    }

    @Override
    public long getLong(String path) {
        return configuration.getLong(path);
    }

    @Override
    public List<Long> getLongList(String path) {
        return configuration.getLongList(path);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return configuration.getBooleanList(path);
    }

    @Override
    public List<Byte> getByteList(String path) {
        return configuration.getByteList(path);
    }

    @Override
    public List<Character> getCharList(String path) {
        return configuration.getCharList(path);
    }

    @Override
    public List<Float> getFloatList(String path) {
        return configuration.getFloatList(path);
    }

    @Override
    public Object get(String path) {
        return configuration.get(path);
    }

    @Override
    public Object get(String path, Object def) {
        return configuration.get(path,def);
    }

    public void load() {
        configuration = loadConfig(file);
    }

    public void saveConfig(File fileToSave) {
        if (!fileToSave.getParentFile().exists()) {
            boolean createFile = fileToSave.getParentFile().mkdirs();
            if(createFile) {
                logs.info("&7Folder created!!");
            }
        }

        if (!fileToSave.exists()) {
            try (InputStream in = resource) {
                if(in != null) {
                    Files.copy(in, fileToSave.toPath());
                } else {
                    logs.info("Resource is null");
                    logs.info("Creating a empty file for " + fileToSave.getName());
                    boolean created = fileToSave.createNewFile();
                    if (created) {
                        logs.info("File created!");
                    }
                }
            } catch (Exception exception) {
                logs.error(String.format("A error occurred while copying the config %s to the plugin data folder. Error: %s", fileToSave.getName(), exception));
                logs.error(exception);
            }
        }
    }

    private PluginConfiguration loadConfig(File file) {
        if (!file.exists()) {
            saveConfig(file);
        }

        PluginConfiguration cnf = null;

        try {
            cnf = YamlConfiguration.load(file);
        } catch (Exception exception) {
            logs.error("Can't load: " + file.getName() + ".!");
            logs.error(exception);
        }

        logs.info(String.format("&7File &e%s &7has been loaded", file.getName()));
        return cnf;
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

        PluginConfiguration section = configuration.getSection(path);

        if(section == null) {
            return rx;
        }

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
    public boolean getStatus(String path, boolean def) {
        return configuration.getBoolean(path,def);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path,value);
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return (Set<String>) configuration.getKeys();
    }

    @Override
    public Control getSection(String path) {
        return new DefaultControlSectionBuilder(
                file,
                logs,
                configuration,
                configuration.getSection(path)
        );
    }

    @Override
    public String getString(String path, String def) {
        return configuration.getString(path,def);
    }

    @Override
    public String getString(String path) {
        return configuration.getString(path,"&cInvalid Path: &e" + path);
    }

    public Object getObjectConfiguration() {
        return configuration;
    }
}

