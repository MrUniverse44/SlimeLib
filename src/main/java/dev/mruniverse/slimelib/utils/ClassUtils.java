package dev.mruniverse.slimelib.utils;

public class ClassUtils {

    /**
     * Check if a class has a certain method. See {@link Class#getMethod(String, Class[])}
     * @param paramClass  The class to check
     * @param method      The method to check for
     * @param parameter   Method parameter types
     * @return <code>true</code> if the class has the method, <code>false</code> if not
     */
    public static boolean hasMethod(Class<?> paramClass, String method, Class<?>... parameter) {
        try {
            paramClass.getMethod(method, parameter);
            return true;
        } catch (NoSuchMethodException ignored) {
            return false;
        }
    }

}
