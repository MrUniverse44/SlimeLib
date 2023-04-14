package me.blueslime.slimelib.commands.manager;

import me.blueslime.slimelib.commands.command.Command;
import me.blueslime.slimelib.commands.storage.CommandStorage;

import java.util.List;

public interface SlimeCommandManager {

    CommandStorage<Command, List<Command>> getStorage();
}
