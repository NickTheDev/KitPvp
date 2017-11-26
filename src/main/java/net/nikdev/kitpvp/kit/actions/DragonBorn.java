package net.nikdev.kitpvp.kit.actions;

import net.nikdev.kitpvp.kit.KitAction;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;

/**
 * Action implementation for the Dragon Born kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class DragonBorn implements KitAction {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.BLACK), ItemBuilder.builder(Material.IRON_CHESTPLATE),
                ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.BLACK));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD));

        fillSoup(user);
    }

}
