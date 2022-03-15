package dev.mruniverse.slimelib.utils;

import dev.mruniverse.slimelib.SlimeFiles;

@SuppressWarnings("unused")
public class SlimeHelper {
    public static <T extends Enum<T> & SlimeFiles> SlimeFiles[] process(Class<T> paramClass) {
        return paramClass.getEnumConstants();
    }
}
