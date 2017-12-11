package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

/**
 * Action implementation for the Assassin kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Assassin implements KitCallback {

    @Override
    public void give(User user) {
        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 3), ItemBuilder.builder(Material.SULPHUR)
                .name("&e&lTurn Invisible").lore(Collections.singleton("&f&lClick to activate.")));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 3));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"Turn Invisible")) {
            if(user.getCache().contains("assassin-invisible-cooldown")) {
                Lang.sendTo(user, Lang.COOLDOWN);

                return;
            }

            user.toPlayer().getWorld().getPlayers().forEach(player -> player.hidePlayer(user.toPlayer()));

            user.getCache().set("assassin-invisible-cooldown", true);

            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> {
                user.toPlayer().getWorld().getPlayers().forEach(player -> player.showPlayer(user.toPlayer()));
                user.getCache().remove("assassin-invisible-cooldown");

            }, 160);

        }

    }

}
