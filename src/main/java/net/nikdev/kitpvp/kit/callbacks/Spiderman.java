package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Action implementation for the Spiderman kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Spiderman implements KitCallback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS),
                ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD));
        user.give(ItemBuilder.builder(Material.WEB).name("&e&lShoot Web").lore(Collections.singleton("&f&lRight click to activate.")));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"Shoot Web")) {
            if(user.getCache().contains("spiderman-web-cooldown")) {
                Lang.sendTo(user, Lang.COOLDOWN);

                return;
            }

            Arrow fake = user.toPlayer().launchProjectile(Arrow.class);

            fake.setVelocity(user.toPlayer().getEyeLocation().getDirection().multiply(1.1));
            fake.setPassenger(user.toPlayer());

            user.getCache().set("spiderman-web-cooldown", true);
            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("spiderman-web-cooldown"), 60);
        }

    }

}
