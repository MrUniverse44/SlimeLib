// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls;

import me.blueslime.slimelib.impls.registered.RegistrationData;

import java.lang.reflect.Array;

public interface Implementer {
    /**
     * Calls the {@link Implements#fetch(Class)}
     * @param clazz to search
     * @return instance or null
     * @param <T> instance type
     */
    default <T> T fetch(Class<T> clazz) {
        return Implements.fetch(clazz);
    }

    /**
     * Calls the {@link Implements#fetch(Class, String)}
     * @param clazz to search
     * @param identifier for this search
     * @return instance or null
     * @param <T> instance type
     */
    default <T> T fetch(Class<T> clazz, String identifier) {
        return Implements.fetch(clazz, identifier);
    }

    /**
     * register implement set, collection, map or list
     * @param type of the set parameter
     * @param values for this set
     */
    default <T, V extends T> ImplementFactory<T, V> registerImpls(Class<T> type, V... values) {
        return new ImplementFactory<>(values);
    }

    /**
     * register implement set, collection, map or list
     * @param type of the set parameter
     * @param values for this set
     */
    default <T, V extends T> ImplementFactory<T, V> registerImpls(Class<T> type, Class<V>... values) {
        return new ImplementFactory<>(createInstances(values));
    }

    @SuppressWarnings("unchecked")
    default <T> T[] createInstances(Class<? extends T>... values) {
        T[] instances = (T[]) Array.newInstance(values[0], values.length);
        for (int i = 0; i < values.length; i++) {
            instances[i] = createInstance(values[i]);
        }
        return instances;
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * @param newValue result
     * in the plugin disable event
     * @return registered implement
     * @param <T> value
     */
    default <T> T registerImpl(Class<T> clazz, T newValue) {
        return Implements.setEntry(clazz, newValue);
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * @param identifier of your implementation
     * @param newValue result
     * @return registered implement
     * @param <T> value
     */
    default <T> T registerImpl(Class<T> clazz, String identifier, T newValue) {
        return Implements.setEntry(clazz, identifier, newValue);
    }

    /**
     * Unregister an implement
     * @param data to unregister
     */
    default void unregisterImpl(RegistrationData data) {
        Implements.unregister(data);
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * @param newValue result
     * @param persist if true this module will be keep in memory, if not it will be removed
     * in the plugin disable event
     * @return registered implement
     * @param <T> value
     */
    default <T> T registerImpl(Class<T> clazz, T newValue, boolean persist) {
        return Implements.setEntry(clazz, newValue, persist);
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * @param identifier of your implementation
     * @param newValue result
     * @param persist if true this module will be keep in memory, if not it will be removed
     * in the plugin disable event
     * @return registered implement
     * @param <T> value
     */
    default <T> T registerImpl(Class<T> clazz, String identifier, T newValue, boolean persist) {
        return Implements.setEntry(clazz, identifier, newValue, persist);
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * in the plugin disable event
     * @return registered implement
     * @param <T> value
     */
    default  <T> T registerImpl(Class<T> clazz) {
        return Implements.setEntry(clazz, createInstance(clazz));
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * @param identifier of your implementation
     * @return registered implement
     * @param <T> value
     */
    default <T> T registerImpl(Class<T> clazz, String identifier) {
        return Implements.setEntry(clazz, identifier, createInstance(clazz));
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * @param persist if true this module will be keep in memory, if not it will be removed
     * in the plugin disable event
     * @return registered implement
     * @param <T> value
     */
    default <T> T registerImpl(Class<T> clazz, boolean persist) {
        return Implements.setEntry(clazz, createInstance(clazz), persist);
    }

    /**
     * Register a new implement
     * @param clazz to be registered
     * @param identifier of your implementation
     * @param persist if true this module will be keep in memory, if not it will be removed
     * in the plugin disable event
     * @return registered implement
     * @param <T> value
     */
    default <T> T registerImpl(Class<T> clazz, String identifier, boolean persist) {
        return Implements.setEntry(clazz, identifier, createInstance(clazz), persist);
    }

    default <T> T createInstance(Class<T> clazz) {
        return Implements.createInstance(clazz);
    }
}
