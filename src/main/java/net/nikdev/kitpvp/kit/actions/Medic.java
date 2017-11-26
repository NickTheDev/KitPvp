package net.nikdev.kitpvp.kit.actions;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.kit.KitAction;
import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static net.nikdev.kitpvp.lang.LangKeys.COOLDOWN;
import static net.nikdev.kitpvp.lang.LangKeys.MEDIC_GROUP_HEAL;
import static net.nikdev.kitpvp.lang.LangKeys.MEDIC_PERSONAL_HEAL;

/**
 * Action implementation for the Medic kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Medic implements KitAction {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 3),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.LEATHER_BOOTS).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 3));

        user.give(ItemBuilder.builder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 1));
        user.give(ItemBuilder.builder(Material.REDSTONE).name("&e&lMedkit"));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item, "&e&lMedkit")) {
            if(right) {
                if(user.getCache().contains("medic-personal-cooldown")) {
                    LangKeys.sendTo(user, COOLDOWN);

                    return;
                }

                LangKeys.sendTo(user, MEDIC_PERSONAL_HEAL);
                user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100, 2));

                user.getCache().set("medic-personal-cooldown", true);
                Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("medic-personal-cooldown"), 200);

            } else {
                if(user.getCache().contains("medic-group-cooldown")) {
                    LangKeys.sendTo(user, COOLDOWN);

                    return;
                }

                LangKeys.sendTo(user, MEDIC_GROUP_HEAL);
                user.toPlayer().getNearbyEntities(5, 5, 5).stream().filter(entity -> entity instanceof Player).forEach(entity ->
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100, 2)));

                user.getCache().set("medic-group-cooldown", true);
                Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("medic-group-cooldown"), 400);

            }

        }

    }

}
