package me.blueslime.slimelib.commands.command;

import me.blueslime.slimelib.source.SlimeSource;

import java.util.ArrayList;
import java.util.List;

public interface SlimeCommand {
    List<String> array = new ArrayList<>();

    /**
     * This will be the main command for this command-class.
     * @return String - Command
     */
    String getCommand();

    /**
     * Add extra aliases to execute the command
     * @return List - Aliases
     */
    default List<String> getAliases() {
        return array;
    }

    /**
     * Execute the command
     * @param sender the sender using the command
     * @param commandLabel the command-alias used
     * @param args arguments of the command executed
     */
    void execute(SlimeSource sender, String commandLabel, String[] args);

    /**
     * Add Tab Complete to your command
     * @param sender the sender using the command
     * @param commandLabel the command-alias used
     * @param args arguments of the tab complete
     * @return List - the tab complete arguments
     */
    @SuppressWarnings("unused")
    default List<String> onTabComplete(SlimeSource sender, String commandLabel, String[] args) {
        return array;
    }
}
