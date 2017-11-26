package net.nikdev.kitpvp.command.defaults;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.command.ArgInfo;
import net.nikdev.kitpvp.command.Argument;
import net.nikdev.kitpvp.command.CommandException;
import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.location.Cuboid;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import static net.nikdev.kitpvp.lang.LangKeys.LOCATIONS_SET;
import static net.nikdev.kitpvp.lang.LangKeys.MUST_SET_LOCATIONS;
import static net.nikdev.kitpvp.lang.LangKeys.SPAWN_OUTSIDE_ERROR;

/**
 * Argument that sets the spawn and spawn region based on the {@link User}'s cache and current location.s
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
            LangKeys.sendTo(user, MUST_SET_LOCATIONS);

            return;
        }

        Cuboid region = new Cuboid((Location) user.getCache().get("first-location").get(), (Location) user.getCache().get("second-location").get());

        if(!region.isInside(user.toPlayer().getLocation())) {
            LangKeys.sendTo(user, SPAWN_OUTSIDE_ERROR);

            return;
        }

        KitPvp.get().getLocations().setRegion(region);
        KitPvp.get().getLocations().setSpawn(user.toPlayer().getLocation());

        LangKeys.sendTo(user, LOCATIONS_SET);
    }

}
