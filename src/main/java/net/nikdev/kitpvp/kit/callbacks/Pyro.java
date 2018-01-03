package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Cooldowns;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
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
public class Pyro implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.CHAINMAIL_HELMET).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1), ItemBuilder.builder(Material.CHAINMAIL_CHESTPLATE),
                ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.CHAINMAIL_BOOTS));

        user.give(ItemBuilder.builder(Material.IRON_SWORD).enchant(Enchantment.FIRE_ASPECT, 2), ItemBuilder.builder(Material.IRON_HOE).name("&e&lFire Cannon")
                .lore(Collections.singleton("&f&lClick to activate.")));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"Fire Cannon")) {
            if(Cooldowns.check(user, "pyro-cannon")) {
                return;
            }

            Fireball ball = user.toPlayer().launchProjectile(Fireball.class);

            ball.setDirection(user.toPlayer().getLocation().getDirection().multiply(2));
            ball.setYield(0);
            ball.setIsIncendiary(false);
            ball.setShooter(user.toPlayer());

            Cooldowns.start(user, "pyro-cannon", 160);
        }

    }

}
