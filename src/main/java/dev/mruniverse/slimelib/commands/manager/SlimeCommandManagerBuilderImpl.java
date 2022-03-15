package dev.mruniverse.slimelib.commands.manager;

import dev.mruniverse.slimelib.commands.SlimeCommands;
import dev.mruniverse.slimelib.commands.injector.SlimeCommandInjector;
import dev.mruniverse.slimelib.commands.inputs.InputAdapter;
import dev.mruniverse.slimelib.commands.inputs.adapters.StringAdapter;
import dev.mruniverse.slimelib.commands.storage.CommandStorage;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class SlimeCommandManagerBuilderImpl implements SlimeCommandManagerBuilder {
    private final Map<Class<?>, InputAdapter<?>> adapterMap = new HashMap<>();
    private SlimeCommandInjector injector;
    private CommandStorage<?, ?> storage;

    @Override
    public SlimeCommandManagerBuilderImpl inject(SlimeCommandInjector injector) {

        if (injector == null) {
            return this;
        }

        this.injector = injector;
        return this;
    }

    @Override
    public SlimeCommandManagerBuilderImpl storage(CommandStorage<?, ?> storage) {

        if (storage == null) {
            return this;
        }

        this.storage = storage;
        return this;
    }

    @Override
    public SlimeCommandManager register() {
        final SlimeCommandManager manager = new SlimeCommandManager() {
            @Override
            public SlimeCommandInjector getInjector() {
                return injector;
            }

            @Override
            public Map<Class<?>, InputAdapter<?>> getAdapterMap() {
                return adapterMap;
            }

            @Override
            public CommandStorage<?, ?> getStorage() {
                return storage;
            }
        };

        SlimeCommands.setCommandManager(manager);

        SlimeCommands.registerInputAdapter(String.class, new StringAdapter());

        return manager;
    }
}
