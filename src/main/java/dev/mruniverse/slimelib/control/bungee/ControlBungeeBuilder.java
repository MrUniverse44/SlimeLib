package dev.mruniverse.slimelib.control.bungee;

import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.control.Control;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class ControlBungeeBuilder implements Control {

    private final InputStream resource;

    private final SlimeLogs logs;

    private final File file;

    private Configuration configuration;



    public ControlBungeeBuilder(SlimeLogs logs, File file, InputStream resource) {
        this.file = file;
        this.logs = logs;
        this.resource = resource;
        load();
    }

    public ControlBungeeBuilder(SlimeLogs logs,File file) {
        this.file = file;
        this.logs = logs;
        this.resource = null;
        load();
    }

    @Override
    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        }catch (Exception exception) {
            logs.error("Can't save file: " + file.getName());
            logs.error(exception);
        }
    }

    @Override
    public List<String> getColoredStringList(String path) {
        List<String> coloredList = new ArrayList<>();
        configuration.getStringList(path).forEach(text -> coloredList.add(ChatColor.translateAlternateColorCodes('&', text)));
        return coloredList;
    }

    @Override
    public String getColoredString(String path) {
        return ChatColor.translateAlternateColorCodes('&',configuration.getString(path,"&cInvalid path: &e" + path));
    }

    @Override
    public String getColoredString(String path,String def) {
        return ChatColor.translateAlternateColorCodes('&',configuration.getString(path,def));
    }

    @Override
    public Object get(String path) { return configuration.get(path); }

    @Override
    public Object get(String path,Object def) { return configuration.get(path,def); }

    @Override
    public long getLong(String path,long def) { return configuration.getLong(path,def); }

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
    public String getStringWithoutColors(String path) {
        return ChatColor.stripColor(configuration.getString(path,"Invalid path: " + path));
    }

    @Override
    public String getStringWithoutColors(String path,String def) {
        return ChatColor.stripColor(configuration.getString(path,def));
    }

    @Override
    public void reload() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        }catch (Exception exception) {
            logs.error("Can't reload file: " + file.getName());
            logs.error(exception);
        }
    }

    public void load() {
        configuration = loadConfig(file);
    }

    public void saveConfig(File fileToSave) {
        if (!fileToSave.getParentFile().exists()) {
            boolean createFile = fileToSave.getParentFile().mkdirs();
            if(createFile) logs.info("&7Folder created!!");
        }

        if (!fileToSave.exists()) {
            try (InputStream in = resource) {
                if(in != null) {
                    Files.copy(in, fileToSave.toPath());
                }
            } catch (Exception exception) {
                logs.error(String.format("A error occurred while copying the config %s to the plugin data folder. Error: %s", fileToSave.getName(), exception));
                logs.error(exception);
            }
        }
    }

    private Configuration loadConfig(File file) {
        if (!file.exists()) {
            saveConfig(file);
        }

        Configuration cnf = null;
        try {
            cnf = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (Exception e) {
            logs.warn(String.format("A error occurred while loading the settings file. Error: %s", e));
            e.printStackTrace();
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
    public File getFile() {
        return file;
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
    public boolean getStatus(String path, boolean def) {
        return configuration.getBoolean(path,def);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path,value);
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return new HashSet<>(configuration.getKeys());
    }

    @Override
    public Control getSection(String path) {
        return new ControlBungeeSectionBuilder(file, logs, configuration, configuration.getSection(path));
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
