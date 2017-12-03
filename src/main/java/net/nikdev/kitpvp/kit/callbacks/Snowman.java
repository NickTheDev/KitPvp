package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.menu.ItemBuilder;
import org.bukkit.Bukkit;
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
public class Snowman implements KitCallback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.WHITE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS),
                ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.WHITE).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1)
                .enchant(Enchantment.DURABILITY, 3));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD));
        user.give(ItemBuilder.builder(Material.IRON_HOE).name("&e&lShoot Snowballs").lore(Collections.singleton("&f&lRight click to activate.")));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"&e&lShoot Snowballs")) {
            if(user.getCache().contains("snowman-shoot-cooldown")) {
                Lang.sendTo(user, Keys.COOLDOWN);

                return;
            }

            Snowball snowball = user.toPlayer().launchProjectile(Snowball.class);

            snowball.setVelocity(user.toPlayer().getLocation().getDirection().multiply(2));
            snowball.setShooter(user.toPlayer());

            user.getCache().set("snowman-shoot-cooldown", true);
            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("snowman-shoot-cooldown"), 40);
        }

    }

}
