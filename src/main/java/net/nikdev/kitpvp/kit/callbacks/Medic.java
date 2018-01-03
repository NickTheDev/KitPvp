package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.Cooldowns;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

/**
 * Action implementation for the Medic kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Medic implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 3),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.LEATHER_BOOTS).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).enchant(Enchantment.DURABILITY, 3));

        user.give(ItemBuilder.builder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 1), ItemBuilder.builder(Material.REDSTONE).name("&e&lMedkit")
                .lore(Collections.singleton("&f&lRight click to activate personal heal, left click to heal nearby players.")));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item, "Medkit")) {
            if(right) {
                if(Cooldowns.check(user, "medic-personal")) {
                    return;
                }

                Lang.sendTo(user, Lang.MEDIC_PERSONAL_HEAL);
                user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100, 2));

                Cooldowns.start(user, "medic-personal", 200);

            } else {
               if(Cooldowns.check(user, "medic-group")) {
                   return;
               }

                Lang.sendTo(user, Lang.MEDIC_GROUP_HEAL);
                user.toPlayer().getNearbyEntities(5, 5, 5).stream().filter(entity -> entity instanceof Player).forEach(entity ->
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100, 2)));

                Cooldowns.start(user, "medic-group", 400);
            }

        }

    }

}
