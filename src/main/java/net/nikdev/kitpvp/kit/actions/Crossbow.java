package net.nikdev.kitpvp.kit.actions;

import net.nikdev.kitpvp.kit.KitAction;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Action implementation for the Crossbow kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Crossbow implements KitAction {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .enchant(Enchantment.DURABILITY, 5), ItemBuilder.builder(Material.LEATHER_CHESTPLATE).armorColor(Color.RED)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 5), ItemBuilder.builder(Material.LEATHER_LEGGINGS)
                .armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 5), ItemBuilder.builder(Material.LEATHER_BOOTS)
                .armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 5));

        user.give(ItemBuilder.builder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 1));
        user.give(ItemBuilder.builder(Material.BOW).enchant(Enchantment.ARROW_KNOCKBACK, 1).enchant(Enchantment.ARROW_DAMAGE, 5));
        user.give(ItemBuilder.builder(Material.ARROW).amount(48));

        fillSoup(user);
    }

}
