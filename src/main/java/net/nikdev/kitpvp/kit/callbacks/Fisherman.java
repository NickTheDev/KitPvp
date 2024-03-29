package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Action implementation for the Fisherman kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Fisherman implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.GOLD_HELMET), ItemBuilder.builder(Material.IRON_CHESTPLATE),
                ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.IRON_BOOTS));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 1), ItemBuilder.builder(Material.FISHING_ROD).name("&e&lFishin' Rod"));

        fillSoup(user);
    }

}
