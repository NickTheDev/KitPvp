package net.nikdev.kitpvp.command;

import org.bukkit.command.CommandSender;

/**
 * Argument of the {@link CommandManager}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public interface Argument {

    /**
     * Executes this command argument with the specified information.
     *
     * @param sender Sender of the command.
     * @param args Arguments of the command.
     * @throws CommandException Thrown if an error occurs executing.
     */
    void execute(CommandSender sender, String[] args) throws CommandException;

}
