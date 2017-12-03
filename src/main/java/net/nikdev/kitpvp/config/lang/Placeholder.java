package net.nikdev.kitpvp.config.lang;

/**
 * Represents a text placeholder. The format of an id is an all uppercase string surround by percent symbols (i. e. %NAME%).
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Placeholder {

    private final String id, value;

    /**
     * Creates a new placeholder with the specified information.
     *
     * @param id Id of this placeholder.
     * @param value Value of this placeholder.
     */
    private Placeholder(String id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Gets the id of this placeholder.
     *
     * @return This placeholder's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the value of this placeholder.
     *
     * @return This placeholder's value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Applies this placeholder to the specified text and returns the modified text.
     *
     * @param text Text to modify.
     * @return Modified text with the placeholder value.
     */
    public String apply(String text) {
        return text.replaceAll(id, value);
    }

    /**
     * Utility to easily create a new placeholder. The id will be put into the correct format if
     * it is not already in it.
     *
     * @param id Id of the placeholder.
     * @param value Value of the placeholder.
     * @return New placeholder with the specified information.
     */
    public static Placeholder of(String id, Object value) {
        return new Placeholder(id.startsWith("%") ? id : "%" + id.toUpperCase() + "%", value.toString());
    }

}