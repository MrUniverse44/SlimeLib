package dev.mruniverse.slimelib.events.event;

import dev.mruniverse.slimelib.SlimePlatform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Events {

    SlimePlatform platform = SlimePlatform.getAutomatically();

}
