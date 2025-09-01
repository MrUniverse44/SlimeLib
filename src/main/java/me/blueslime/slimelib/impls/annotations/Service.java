// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {

    Class<?>[] loadAfterOf() default {};
    Class<?>[] loadBeforeOf() default {};
    int platform() default 2;
    boolean register() default true;
    int priority() default 0;
    String key() default "";
    Class<? extends ServiceHandler> handler() default ServiceHandler.class;

}
