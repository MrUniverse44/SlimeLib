package dev.mruniverse.slimelib.commands.inputs.adapters;

import dev.mruniverse.slimelib.commands.SlimeLabel;
import dev.mruniverse.slimelib.commands.inputs.InputAdapter;

public class BooleanAdapter implements InputAdapter<Boolean> {

    @Override
    public Boolean execute(SlimeLabel label) {
        return Boolean.parseBoolean(label.getLabel());
    }

    @Override
    public boolean canExecute(String argument) {
        return false;
    }

}
