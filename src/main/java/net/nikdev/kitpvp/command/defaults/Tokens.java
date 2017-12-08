package net.nikdev.kitpvp.command.defaults;

import net.nikdev.kitpvp.command.ArgInfo;
import net.nikdev.kitpvp.command.Argument;
import net.nikdev.kitpvp.command.CommandException;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * Argument that allows senders to check token balances.
 *
 * @author NickTheDev
 * @since 1.0
 */
@ArgInfo(name = "tokens", help = "Allows players to check token balances.")
public class Tokens implements Argument {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if(args.length == 1) {
            if(sender instanceof Player) {
                Lang.sendTo(sender, Lang.PERSONAL_TOKEN_CHECK, Placeholder.of("amount", User.get(sender.getName()).get().getStats().getTokens()));

            } else {
                throw new CommandException(Lang.get(Lang.ATTEMPT_CONSOLE_PERSONAL));
            }

            return;
        }

        Optional<User> target = User.get(args[1]);

        if(!target.isPresent()) {
            throw new CommandException(Lang.get(Lang.UNKNOWN_TARGET, Placeholder.of("target", args[1])));
        }

        Lang.sendTo(sender, Lang.TOKEN_CHECK, Placeholder.of("target", args[1]), Placeholder.of("amount", target.get().getStats().getTokens()));
    }

}
