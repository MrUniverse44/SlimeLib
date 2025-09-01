package me.blueslime.slimelib.impls.service;

public @interface Phase {
    int id() default State.SERVICE_INITIALIZE;
}
