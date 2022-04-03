package dev.mruniverse.slimelib.commands.manager;

import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.storage.CommandStorage;

import java.util.List;

public interface SlimeCommandManager {

    CommandStorage<Command, List<Command>> getStorage();
}
