// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls.executer;

import me.blueslime.slimelib.impls.Implementer;
import me.blueslime.slimelib.impls.Implements;
import me.blueslime.slimelib.impls.module.Module;
import me.blueslime.slimelib.utils.consumer.PluginConsumer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ModuleObjects<T extends Module> implements Implementer {

    private final Set<Exception> exceptionSet = new HashSet<>();
    private final Set<T> moduleSet = new HashSet<>();

    public ModuleObjects() {

    }

    @SafeVarargs
    public final ModuleObjects<T> at(T... module) {
        Collections.addAll(moduleSet, module);
        return this;
    }

    @SafeVarargs
    public final ModuleObjects<T> at(Class<? extends T>... module) {
        for (Class<? extends T> clazz : module) {
            PluginConsumer.process(
                () -> moduleSet.add(Implements.createInstance(clazz)),
                exceptionSet::add
            );
        }
        return this;
    }

    public ModuleObjects<T> onThrow(Consumer<Exception> consumer) {
        for (Exception exception : exceptionSet) {
            consumer.accept(exception);
        }
        return this;
    }

    public ModuleObjects<T> execute(Consumer<T> execute) {
        moduleSet.forEach(
            module -> PluginConsumer.process(
                () -> execute.accept(module), exceptionSet::add
            )
        );
        return this;
    }

}
