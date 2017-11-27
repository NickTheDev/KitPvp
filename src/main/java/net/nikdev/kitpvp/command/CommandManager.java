package net.nikdev.kitpvp.command;

import net.nikdev.kitpvp.command.defaults.SetLocations;
import net.nikdev.kitpvp.command.defaults.Wand;
import net.nikdev.kitpvp.lang.LangKeys;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static net.nikdev.kitpvp.lang.LangKeys.*;

/**
 * Manages command {@link Argument}s and executing them.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class CommandManager implements CommandExecutor {

    private final Map<ArgInfo, Argument> arguments = new HashMap<>();

    /**
     * Creates a new command manager and registers all default arguments.
     */
    public CommandManager() {
        registerAll();
    }

    /**
     * Gets the argument info with the specified name.
     *
     * @param name Name of the argument.
     * @return Argument with the specified name.
     */
    private Optional<ArgInfo> getArgument(String name) {
        return arguments.keySet().stream().filter(args -> args.name().equals(name)).findFirst();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            LangKeys.sendTo(sender, NO_ARGS);

            return false;
        }

        if(!getArgument(args[0]).isPresent()) {
            LangKeys.sendTo(sender, UNKNOWN_ARGS);

            return false;
        }

        ArgInfo info = getArgument(args[0]).get();

        if(!info.permission().isEmpty() && !sender.hasPermission(info.permission())) {
            LangKeys.sendTo(sender, NO_PERMISSION);

            return false;
        }

        if(info.userOnly() && !(sender instanceof Player)) {
            LangKeys.sendTo(sender, NOT_PLAYER);

            return false;
        }

        try {
            arguments.get(info).execute(sender, args);

        } catch (CommandException e) {
            LangKeys.sendTo(sender, ARG_ERROR);
            e.printStackTrace();

            return false;
        }

        return true;
    }

    /**
     * Registers the specified argument to this command manager.
     *
     * @param argument Argument to register.
     * @param <T> Type of argument.
     */
    private <T extends Argument> void register(T argument) {
        if(arguments.containsValue(argument)) {
            return;
        }

        arguments.put(argument.getClass().getAnnotation(ArgInfo.class), argument);
    }

    /**
     * Registers all default arguments.
     */
    private void registerAll() {
        Arrays.asList(new Help(), new Wand(), new SetLocations()).forEach(this::register);
    }

    /**
     * Help argument which tells a command sender how to use arguments.
     *
     * @author NickTheDev
     * @since 1.0
     */
    @ArgInfo(name = "help", help = "Displays a list of all commands.")
    private final class Help implements Argument {

        @Override
        public void execute(CommandSender sender, String[] args) throws CommandException {
            LangKeys.sendTo(sender, HELP);

            arguments.keySet().forEach(info -> LangKeys.sendTo(sender, HELP_TEMPLATE, Placeholder.of("name", info.name()), Placeholder.of("help", info.help())));
        }

    }

}
