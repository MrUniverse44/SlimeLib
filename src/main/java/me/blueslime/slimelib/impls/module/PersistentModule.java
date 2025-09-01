// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls.module;

public interface PersistentModule extends RegisteredModule {
    @Override
    default boolean isPersistent() {
        return true;
    }

    @Override
    default void unregisterImplementedModule() {

    }
}
