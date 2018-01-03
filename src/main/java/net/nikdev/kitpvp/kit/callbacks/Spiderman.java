package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Action implementation for the Spiderman kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Spiderman implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS),
                ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD));
        user.give(ItemBuilder.builder(Material.WEB).name("&e&lShoot Web").lore(Collections.singleton("&f&lClick to activate.")));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        // TODO Ability doesn't make any sense so gotta figure that out.
    }

}
