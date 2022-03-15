package dev.mruniverse.slimelib.commands;

import dev.mruniverse.slimelib.commands.inputs.InputAdapter;
import dev.mruniverse.slimelib.commands.manager.SlimeCommandManager;
import dev.mruniverse.slimelib.commands.manager.SlimeCommandManagerBuilder;
import dev.mruniverse.slimelib.commands.manager.SlimeCommandManagerBuilderImpl;

public class SlimeCommands {
    private static SlimeCommandManager manager;

    public static SlimeCommandManager getManager() {
        return manager;
    }

    public static void setCommandManager(SlimeCommandManager manager) {
        SlimeCommands.manager = manager;
    }

    //TODO: Command Builder

    public static SlimeCommandManagerBuilder builder() {
        return new SlimeCommandManagerBuilderImpl();
    }

    public static <T> void registerInputAdapter(Class<T> clazz, InputAdapter<T> adapter) {
        getManager().getAdapterMap().put(clazz, adapter);
    }
}
