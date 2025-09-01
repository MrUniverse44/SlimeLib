// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls.module;

import me.blueslime.slimelib.impls.Implements;

/**
 * This is a simple module interface
 * This is used for registerModule in the main class
 * to allow the usage with instance.getModule method
 * with this method you can get the instance of this module
 * in other classes.
 */
public interface Module {

    default void initialize() {

    }

    default void reload() {

    }

    default void shutdown() {
        unregisterImplementedModule();
    }

    /**
     * Register this module to the Implements
     */
    default void registerImplementedModule() {
        Implements.register(this);
    }

    /**
     * Register this or other module to the Implements
     * @param instance to be registered
     */
    default void registerImplementedModule(Object instance) {
        Implements.register(instance);
    }

    /**
     * Unregister implemented module
     */
    default void unregisterImplementedModule() {
        Implements.unregister(this);
    }

    default boolean isPersistent() {
        return false;
    }
}
