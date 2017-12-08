package net.nikdev.kitpvp.command.defaults;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.command.ArgInfo;
import net.nikdev.kitpvp.command.Argument;
import net.nikdev.kitpvp.command.CommandException;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.util.Cuboid;
import net.nikdev.kitpvp.user.User;
import org.bukkit.command.CommandSender;

/**
 * Argument that sets the spawn and spawn region based on the {@link User}'s cache and current location.
 *
 * @author NickTheDev
 * @since 1.0
 */
@ArgInfo(name = "setlocations", help = "Sets the spawn and spawn region.", permission = "kitpvp.setlocations", userOnly = true)
public class SetLocations implements Argument {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        User user = User.get(sender.getName()).get();

        if(!user.getCache().contains("first-location") || !user.getCache().contains("second-location")) {
            throw new CommandException(Lang.get(Lang.LOCATIONS_NOT_SET));
        }

        Cuboid region = new Cuboid(user.getCache().get("first-location"), user.getCache().get("second-location"));

        if(!region.isInside(user.toPlayer().getLocation())) {
            throw new CommandException(Lang.get(Lang.CANNOT_SPAWN_OUTSIDE));
        }

        KitPvp.get().getLocations().setRegion(region);
        KitPvp.get().getLocations().setSpawn(user.toPlayer().getLocation());

        Lang.sendTo(user, Lang.LOCATIONS_SET);
    }

}
