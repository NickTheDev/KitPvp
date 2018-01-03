package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Action implementation for the Dragon Born kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class DragonBorn implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.BLACK).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.LEATHER_BOOTS)
                        .armorColor(Color.BLACK).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD), ItemBuilder.builder(Material.SKULL_ITEM, (short) 5).name("&e&lPush Players"));

        fillSoup(user);
    }

}
