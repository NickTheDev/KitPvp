package net.nikdev.kitpvp.command.defaults;

import net.nikdev.kitpvp.command.ArgInfo;
import net.nikdev.kitpvp.command.Argument;
import net.nikdev.kitpvp.command.CommandException;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import org.bukkit.command.CommandSender;

import java.util.Optional;

/**
 * Argument that allows administrators to modify {@link User}s' tokens.
 *
 * @author NickTheDev
 * @since 1.0
 */
@ArgInfo(name = "modify", help = "Allows admins to modify players' tokens.", permission = "kitpvp.modify")
public class Modify implements Argument {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if(args.length != 4) {
            Lang.sendTo(sender, Lang.NO_TOKEN_ARGS);

            return;
        }

        Optional<User> target = User.get(args[2]);
        int amount;

        if(!target.isPresent()) {
            throw new CommandException(Lang.get(Lang.UNKNOWN_TARGET, Placeholder.of("target", args[2])));
        }

        try {
            amount = Integer.valueOf(args[3]);

        } catch(NumberFormatException e) {
            throw new CommandException(Lang.get(Lang.INVALID_AMOUNT, Placeholder.of("amount", args[3])));
        }

        switch(args[1]) {
            case "add":
                target.get().getStats().addTokens(amount);
                Lang.sendTo(sender, Lang.TOKENS_UPDATED, Placeholder.of("target", args[2]), Placeholder.of("amount", target.get().getStats().getTokens()));

                break;

            case "remove":
                target.get().getStats().removeTokens(amount);
                Lang.sendTo(sender, Lang.TOKENS_UPDATED, Placeholder.of("target", args[2]), Placeholder.of("amount", target.get().getStats().getTokens()));

                break;

            case "set":
                target.get().getStats().setTokens(amount);
                Lang.sendTo(sender, Lang.TOKENS_UPDATED, Placeholder.of("target", args[2]), Placeholder.of("amount", target.get().getStats().getTokens()));

                break;

            default:
                throw new CommandException(Lang.get(Lang.UNKNOWN_MODIFY, Placeholder.of("operation", args[1])));
        }

    }

}
