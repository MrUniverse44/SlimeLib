// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls.registered;

import lombok.Getter;
import me.blueslime.slimelib.impls.module.Module;

public class RegistrationData {
    @Getter
    private final String identifier;
    private final Class<?> clazz;
    private final Module module;

    private RegistrationData(Class<?> clazz) {
        this(null, clazz, null);
    }

    private RegistrationData(Module module, Class<?> clazz) {
        this(module, clazz, null);
    }

    private RegistrationData(Class<?> clazz, String identifier) {
        this(null, clazz, identifier);
    }

    private RegistrationData(Module module, Class<?> clazz, String identifier) {
        if (clazz == null) {
            throw new NullPointerException("Class cannot be null in a @Register");
        }
        this.identifier = identifier;
        this.module = module;
        this.clazz = clazz;
    }

    public static RegistrationData fromData(Class<?> clazz) {
        return new RegistrationData(clazz);
    }

    public static RegistrationData fromData(Class<?> clazz, String identifier) {
        return new RegistrationData(clazz, identifier);
    }

    public static RegistrationData fromData(Module module, Class<?> clazz) {
        return new RegistrationData(module, clazz);
    }

    public static RegistrationData fromData(Module module, Class<?> clazz, String identifier) {
        return new RegistrationData(module, clazz, identifier);
    }

    public Class<?> getInstance() {
        return clazz;
    }

    public Module getParentModule() {
        return module;
    }

    @Override
    public RegistrationData clone() {
        return new RegistrationData(module, clazz, identifier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationData that = (RegistrationData) o;
        if (!clazz.equals(that.clazz)) return false;
        return identifier != null ? identifier.equals(that.identifier) : that.identifier == null;
    }

    @Override
    public int hashCode() {
        int result = clazz.hashCode();
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }
}
