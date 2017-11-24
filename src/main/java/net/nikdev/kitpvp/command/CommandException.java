package net.nikdev.kitpvp.command;

/**
 * Represents an error that occurs when handling a command execution.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class CommandException extends Exception {

    /**
     * Creates a new command exception with the specified message.
     *
     * @param message Message of this exception.
     */
    public CommandException(String message) {
        super(message);
    }

}
