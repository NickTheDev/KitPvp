package net.nikdev.kitpvp.util;

/**
 * Represents an abstract builder with a specified result type.
 *
 * @author NickTheDev
 * @param <T> Type of result.
 * @since 1.0
 */
public interface AbstractBuilder<T> {

    /**
     * Gets the result of this builder.
     *
     * @return This builder's result.
     */
    T build();

    /**
     * Checks if the specified required builder values are not null and throws an exception if not.
     *
     * @param values Values to check.
     * @throws IllegalArgumentException Thrown if any value is null.
     */
    static void checkRequired(Object... values) {
        for(Object value : values) {
            if(value == null) {
                throw new IllegalArgumentException("Required builder field passed was null.");
            }

        }

    }

}
