package me.blueslime.slimelib.commands.manager;

import me.blueslime.slimelib.commands.command.Command;
import me.blueslime.slimelib.commands.storage.CommandStorage;

import java.util.List;

public class DefaultSlimeCommandManager implements SlimeCommandManager {
    private final CommandStorage<Command, List<Command>> storage;

    public DefaultSlimeCommandManager() {
        this.storage = CommandStorage.create();
    }

    @Override
    public CommandStorage<Command, List<Command>> getStorage() {
        return storage;
    }
}
