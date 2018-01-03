package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Cooldowns;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Action implementation for the Snowman kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Snowman implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.WHITE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS),
                ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.WHITE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .enchant(Enchantment.DURABILITY, 3));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD), ItemBuilder.builder(Material.IRON_HOE).name("&e&lShoot Snowballs")
                .lore(Collections.singleton("&f&lRight click to activate.")));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"&e&lShoot Snowballs")) {
            if(Cooldowns.check(user, "snowman-shoot")) {
                return;
            }

            Snowball snowball = user.toPlayer().launchProjectile(Snowball.class);

            snowball.setVelocity(user.toPlayer().getLocation().getDirection().multiply(2));
            snowball.setShooter(user.toPlayer());

            Cooldowns.start(user, "snowman-shoot", 40);
        }

    }

}
