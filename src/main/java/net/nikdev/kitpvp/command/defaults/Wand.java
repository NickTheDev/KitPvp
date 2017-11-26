package net.nikdev.kitpvp.command.defaults;

import net.nikdev.kitpvp.command.ArgInfo;
import net.nikdev.kitpvp.command.Argument;
import net.nikdev.kitpvp.command.CommandException;
import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import static net.nikdev.kitpvp.lang.LangKeys.WAND_GIVEN;

/**
 * Argument that gives a {@link User} the location wand.
 *
 * @author NickTheDev
 * @since 1.0
 */
@ArgInfo(name = "wand", help = "Gives a player the location wand.", permission = "kitpvp.wand", userOnly = true)
public class Wand implements Argument {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        User.get(sender.getName()).get().give(ItemBuilder.builder(Material.WOOD_HOE).name("&e&lKitPvp Location Wand"));

        LangKeys.sendTo(sender, WAND_GIVEN);
    }

}
