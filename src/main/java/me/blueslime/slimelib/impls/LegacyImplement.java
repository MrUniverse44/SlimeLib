// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, CONSTRUCTOR, PARAMETER})
public @interface LegacyImplement {
    String identifier() default "";
}
