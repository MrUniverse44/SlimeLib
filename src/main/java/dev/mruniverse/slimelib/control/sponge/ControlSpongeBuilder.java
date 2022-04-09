package dev.mruniverse.slimelib.control.sponge;

import dev.mruniverse.slimelib.control.Control;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.utils.Configuration;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public class ControlSpongeBuilder implements Control {

    private final InputStream resource;

    private final SlimeLogs logs;

    private final File file;

    private Configuration configuration;



    public ControlSpongeBuilder(SlimeLogs logs, File file, InputStream resource) {
        this.file = file;
        this.logs = logs;
        this.resource = resource;
        load();
    }

    public ControlSpongeBuilder(SlimeLogs logs,File file) {
        this.file = file;
        this.logs = logs;
        this.resource = null;
        load();
    }

    private void load() {

    }

    @Override
    public List<?> getList(String path) {
        return null;
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return null;
    }

    @Override
    public List<String> getStringList(String path) {
        return null;
    }

    @Override
    public List<String> getColoredStringList(String path) {
        return null;
    }

    @Override
    public List<String> getContent(String path, boolean getKeys) {
        return null;
    }

    @Override
    public List<Integer> getIntList(String path) {
        return null;
    }

    @Override
    public String getColoredString(String path) {
        return null;
    }

    @Override
    public String getColoredString(String path, String def) {
        return null;
    }

    @Override
    public String getStringWithoutColors(String path) {
        return null;
    }

    @Override
    public String getStringWithoutColors(String path, String def) {
        return null;
    }

    @Override
    public String getString(String path, String def) {
        return null;
    }

    @Override
    public String getString(String path) {
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public void reload() {

    }

    @Override
    public long getLong(String path, long def) {
        return 0;
    }

    @Override
    public long getLong(String path) {
        return 0;
    }

    @Override
    public List<Long> getLongList(String path) {
        return null;
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return null;
    }

    @Override
    public List<Byte> getByteList(String path) {
        return null;
    }

    @Override
    public List<Character> getCharList(String path) {
        return null;
    }

    @Override
    public List<Float> getFloatList(String path) {
        return null;
    }

    @Override
    public Object get(String path) {
        return null;
    }

    @Override
    public Object get(String path, Object def) {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public int getInt(String path, int def) {
        return 0;
    }

    @Override
    public int getInt(String path) {
        return 0;
    }

    @Override
    public boolean contains(String path) {
        return false;
    }

    @Override
    public boolean getStatus(String path) {
        return false;
    }

    @Override
    public boolean getStatus(String path, boolean def) {
        return false;
    }

    @Override
    public void set(String path, Object value) {

    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return null;
    }

    @Override
    public Control getSection(String path) {
        return null;
    }
}
