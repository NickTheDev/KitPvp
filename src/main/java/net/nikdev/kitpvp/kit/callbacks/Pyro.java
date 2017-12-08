package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

/**
 * Action implementation for the Pyro kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Pyro implements KitCallback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.CHAINMAIL_HELMET).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1), ItemBuilder.builder(Material.CHAINMAIL_CHESTPLATE),
                ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.CHAINMAIL_BOOTS));

        user.give(ItemBuilder.builder(Material.IRON_SWORD).enchant(Enchantment.FIRE_ASPECT, 2));
        user.give(ItemBuilder.builder(Material.IRON_HOE).name("&e&lFire Cannon").lore(Collections.singleton("&f&lClick to activate.")));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"&e&lFire Cannon")) {
            if(user.getCache().contains("pyro-cannon-cooldown")) {
                Lang.sendTo(user, Keys.COOLDOWN);

                return;
            }

            Fireball ball = user.toPlayer().launchProjectile(Fireball.class);

            ball.setDirection(user.toPlayer().getLocation().getDirection().multiply(2));
            ball.setYield(0);
            ball.setIsIncendiary(false);
            ball.setShooter(user.toPlayer());

            user.getCache().set("pyro-cannon-cooldown", true);
            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("pyro-cannon-cooldown"), 160);
        }

    }

}
