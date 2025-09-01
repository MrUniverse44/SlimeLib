// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD})
public @interface Register {
    String identifier() default "";
}
