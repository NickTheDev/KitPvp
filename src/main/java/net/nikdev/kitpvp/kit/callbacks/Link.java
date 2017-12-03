package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.menu.ItemBuilder;
import net.nikdev.kitpvp.util.Skulls;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

/**
 * Action implementation for the Link kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Link implements KitCallback {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/6bb2e69b3870fe2f4f1ba14a8f9ca8acc9a7520e4e4a9784c19a3a0c9446e4d")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3), ItemBuilder.builder(Material.CHAINMAIL_CHESTPLATE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                ItemBuilder.builder(Material.CHAINMAIL_LEGGINGS).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2), ItemBuilder.builder(Material.LEATHER_BOOTS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));

        user.give(ItemBuilder.builder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 2));
        user.give(ItemBuilder.builder(Material.BOW).enchant(Enchantment.ARROW_DAMAGE, 3));
        user.give(ItemBuilder.builder(Material.ARROW).amount(32));

        fillSoup(user);
    }

}
