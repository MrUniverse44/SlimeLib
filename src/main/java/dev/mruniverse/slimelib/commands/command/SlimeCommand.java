package dev.mruniverse.slimelib.commands.command;

import dev.mruniverse.slimelib.commands.sender.Sender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface SlimeCommand {
    List<String> array = new ArrayList<>();

    String getCommand();

    default List<String> getAliases() {
        return array;
    }

    void execute(@NotNull Sender sender, @NotNull String commandLabel, @NotNull String[] args);

    @SuppressWarnings("unused")
    default List<String> onTabComplete(Sender sender, String commandLabel, String[] args) {
        return array;
    }
}
