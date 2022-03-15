package dev.mruniverse.slimelib.commands.inputs.adapters;

import dev.mruniverse.slimelib.commands.SlimeLabel;
import dev.mruniverse.slimelib.commands.inputs.InputAdapter;

public class StringAdapter implements InputAdapter<String> {

    @Override
    public String execute(SlimeLabel label) {
        return label.getLabel();
    }

    @Override
    public boolean canExecute(String argument) {
        return true;
    }

}