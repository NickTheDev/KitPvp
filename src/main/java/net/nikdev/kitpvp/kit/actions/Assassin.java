package net.nikdev.kitpvp.kit.actions;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.kit.KitAction;
import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

import static net.nikdev.kitpvp.lang.LangKeys.COOLDOWN;

/**
 * Action implementation for the Assassin kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Assassin implements KitAction {

    @Override
    public void give(User user) {
        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 3));
        user.give(ItemBuilder.builder(Material.SULPHUR).name("&e&lTurn Invisible").lore(Collections.singleton("&f&lClick to activate.")));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"&e&lTurn Invisible")) {
            if(user.getCache().contains("assassin-invisible-cooldown")) {
                LangKeys.sendTo(user, COOLDOWN);

                return;
            }

            user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1, false, false));

            user.getCache().set("assassin-invisible-cooldown", true);
            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("assassin-invisible-cooldown"), 160);
        }

    }

}
