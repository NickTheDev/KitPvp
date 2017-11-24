package net.nikdev.kitpvp.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that describes an argument.
 *
 * @author NickTheDev
 * @since 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArgInfo {

    /**
     * Name of this argument.
     *
     * @return Name.
     */
    String name();

    /**
     * Help message of this argument.
     *
     * @return Help message.
     */
    String help();

    /**
     * Permission of this argument.
     *
     * @return Permission.
     */
    String permission() default "";

    /**
     * If this argument is user only.
     *
     * @return User only.
     */
    boolean userOnly() default false;

}
