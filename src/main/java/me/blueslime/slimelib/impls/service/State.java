package me.blueslime.slimelib.impls.service;

public class State {
    public static final int SERVICE_SHUTDOWN = -2;
    public static final int SERVICE_INITIALIZE = -1;
    public static final int FIRST_LOAD = 0;
    public static final int INITIALIZE = 1;
    public static final int ON_LOAD = 2;
    public static final int UNLOAD = 3;
    public static final int RELOAD = 4;
    public static final int RESTART_UNLOAD = 5;
    public static final int RESTART_LOAD = 6;
}
