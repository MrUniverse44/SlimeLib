package dev.mruniverse.slimelib.utils;

import dev.mruniverse.slimelib.SlimeFiles;

@SuppressWarnings("unused")
public class GuardianHelper {
    public static <T extends Enum<T> & SlimeFiles> SlimeFiles[] process(Class<T> paramClass) {
        return paramClass.getEnumConstants();
    }
}
