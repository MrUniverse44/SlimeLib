package dev.mruniverse.slimelib.commands.manager;

import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.injector.SlimeCommandInjector;
import dev.mruniverse.slimelib.commands.inputs.InputAdapter;
import dev.mruniverse.slimelib.commands.storage.CommandStorage;

import java.util.Collection;
import java.util.Map;

public interface SlimeCommandManager {

    SlimeCommandInjector getInjector();

    <T extends Collection<Command>> CommandStorage<Command, T> getStorage();

    Map<Class<?>, InputAdapter<?>> getAdapterMap();
}
