// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls.registered;

import lombok.Getter;
import me.blueslime.slimelib.impls.module.PersistentModule;

public class RegisteredModuleInstance implements PersistentModule {

    @Getter
    private static final RegisteredModuleInstance instance = new RegisteredModuleInstance();

}
