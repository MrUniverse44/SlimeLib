package dev.mruniverse.slimelib.commands.storage;

import dev.mruniverse.slimelib.commands.command.Command;

import java.util.Collection;
import java.util.List;

public interface CommandStorage<E, T extends Collection<E>> {

    static <T extends Collection<Command>> CommandStorage<Command, List<Command>> create() {
        return new DefaultCommandStorage();
    }

    /**
     * The collection that stores all of the commands
     *
     * @return the collection
     */
    T getCollection();

    /**
     * Adds an object to the collection.
     *
     * @param object object to add
     */
    default void add(E object) {
        getCollection().add(object);
    }

}
