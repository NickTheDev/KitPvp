package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import net.nikdev.kitpvp.util.item.Skulls;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 * Action implementation for the Bowser kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Bowser implements KitCallback {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/843059ebb8a5a71e20c0aa752b3ea418afd08aaf21c633744f9c5de15c699bce")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1), ItemBuilder.builder(Material.LEATHER_CHESTPLATE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1).armorColor(Color.YELLOW), ItemBuilder.builder(Material.LEATHER_LEGGINGS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1).armorColor(Color.YELLOW), ItemBuilder.builder(Material.LEATHER_BOOTS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1).armorColor(Color.YELLOW));

        user.give(ItemBuilder.builder(Material.BLAZE_POWDER).name("&e&lBreathe fire"));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        // TODO Ability doesn't make any sense so gotta figure that out.
    }

}
