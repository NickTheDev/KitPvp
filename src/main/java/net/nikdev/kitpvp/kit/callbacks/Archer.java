package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Action implementation for the Archer kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Archer implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET), ItemBuilder.builder(Material.CHAINMAIL_CHESTPLATE),
                ItemBuilder.builder(Material.CHAINMAIL_LEGGINGS), ItemBuilder.builder(Material.LEATHER_BOOTS));

        user.give(ItemBuilder.builder(Material.STONE_SWORD).enchant(Enchantment.DAMAGE_ALL, 1), ItemBuilder.builder(Material.BOW)
                .enchant(Enchantment.ARROW_INFINITE, 1).enchant(Enchantment.ARROW_DAMAGE, 3).enchant(Enchantment.ARROW_KNOCKBACK, 1),
                ItemBuilder.builder(Material.ARROW));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

        fillSoup(user);
    }

}
