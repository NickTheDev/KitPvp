package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Action implementation for the Shark kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Shark implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.DIAMOND_HELMET), ItemBuilder.builder(Material.LEATHER_CHESTPLATE).armorColor(Color.BLUE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .enchant(Enchantment.DURABILITY, 10), ItemBuilder.builder(Material.LEATHER_LEGGINGS).armorColor(Color.BLUE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .enchant(Enchantment.DURABILITY, 10), ItemBuilder.builder(Material.DIAMOND_BOOTS));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD));

        fillSoup(user);
    }

}
