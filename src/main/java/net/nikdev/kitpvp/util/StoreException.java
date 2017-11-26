package net.nikdev.kitpvp.util;

/**
 * Represents an error that occurs interacting with the any type of data storage.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class StoreException extends Exception {

    /**
     * Creates a new store exception with the specified message.
     *
     * @param message Message of this exception.
     * @param cause Cause of this exception.
     */
    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }

}
