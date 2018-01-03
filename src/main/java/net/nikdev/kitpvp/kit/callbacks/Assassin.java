package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Cooldowns;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
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
public class Assassin implements Kit.Callback {

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
            if(Cooldowns.check(user, "assassin-invisible")) {
                return;
            }

            user.toPlayer().getWorld().getPlayers().forEach(player -> player.hidePlayer(user.toPlayer()));
            Cooldowns.start(user, "assassin-invisible", 160, () -> user.toPlayer().getWorld().getPlayers().forEach(player -> player.showPlayer(user.toPlayer())));
        }

    }

}
