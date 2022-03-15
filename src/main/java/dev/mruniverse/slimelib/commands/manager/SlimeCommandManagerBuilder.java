package dev.mruniverse.slimelib.commands.manager;

import dev.mruniverse.slimelib.commands.injector.SlimeCommandInjector;
import dev.mruniverse.slimelib.commands.storage.CommandStorage;

public interface SlimeCommandManagerBuilder {

    SlimeCommandManagerBuilder inject(SlimeCommandInjector injector);

    SlimeCommandManagerBuilder storage(CommandStorage<?, ?> storage);

    SlimeCommandManager register();

}
