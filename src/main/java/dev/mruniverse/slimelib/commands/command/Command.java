package dev.mruniverse.slimelib.commands.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Command {

    /**
     * The description of the command, explaining what it can be used for.
     *
     * @return the description of the command
     */
    String description() default "";

    /**
     * The short description of the command, explaining what it can be used for.
     *
     * @return the short description of the command
     */
    String shortDescription() default "";


    /**
     * The usage of the command.
     *
     * @return the usage of the command.
     */
    String usage() default "/<command> <args>";


}
