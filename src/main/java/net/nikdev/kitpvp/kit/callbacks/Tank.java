package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Action implementation for the Tank kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Tank implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 5),
                ItemBuilder.builder(Material.DIAMOND_CHESTPLATE), ItemBuilder.builder(Material.DIAMOND_LEGGINGS), ItemBuilder.builder(Material.DIAMOND_BOOTS));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD));
        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));

        user.toPlayer().setMaxHealth(35);
        user.toPlayer().setHealth(35);

        fillSoup(user);
    }

}
