package me.blueslime.slimelib.commands.storage;

import me.blueslime.slimelib.commands.command.Command;

import java.util.ArrayList;
import java.util.List;

public class DefaultCommandStorage implements CommandStorage<Command, List<Command>> {

    private final List<Command> commands = new ArrayList<>();

    @Override
    public List<Command> getCollection() {
        return commands;
    }
}
