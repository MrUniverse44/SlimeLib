package dev.mruniverse.guardianstorageapi.utils;

import dev.mruniverse.guardianstorageapi.interfaces.GuardianFiles;

@SuppressWarnings("unused")
public class GuardianHelper {
    public static <T extends Enum<T> & GuardianFiles> GuardianFiles[] process(Class<T> paramClass) {
        return paramClass.getEnumConstants();
    }
}
