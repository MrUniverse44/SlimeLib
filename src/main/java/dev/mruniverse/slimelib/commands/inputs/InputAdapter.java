package dev.mruniverse.slimelib.commands.inputs;

import dev.mruniverse.slimelib.commands.SlimeLabel;

import java.util.ArrayList;
import java.util.List;

public interface InputAdapter<T> {

    T execute(SlimeLabel label);

    boolean canExecute(String argument);

    default List<T> getAutoComplete(String input) {
        return new ArrayList<>();
    }
}
