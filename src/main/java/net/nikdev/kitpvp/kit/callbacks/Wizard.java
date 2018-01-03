package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.menu.kit.SpellSelector;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

/**
 * Action implementation for the Wizard kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Wizard implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.BLACK).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .enchant(Enchantment.DURABILITY, 3), ItemBuilder.builder(Material.LEATHER_CHESTPLATE).armorColor(Color.BLACK).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .enchant(Enchantment.DURABILITY, 3), ItemBuilder.builder(Material.LEATHER_LEGGINGS).armorColor(Color.BLACK).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .enchant(Enchantment.DURABILITY, 3), ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.BLACK).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .enchant(Enchantment.DURABILITY, 3));

        user.give(ItemBuilder.builder(Material.IRON_SWORD), ItemBuilder.builder(Material.STICK).name("&e&lWand").lore(Collections.singleton("" +
                "&f&lLeft click to change spells, right click to activate.")));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item, "Wand")) {
            if(right) {
                if(user.getCache().contains("wizard-spell-cooldown")) {
                    Lang.sendTo(user, Lang.COOLDOWN);

                    return;
                }

                switch(user.getCache().get("wizard-spell", "heal")) {
                    case "heal":
                        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                        Lang.sendTo(user, Lang.WIZARD_HEAL);

                        break;

                    case "poison":
                        user.toPlayer().getNearbyEntities(8, 8, 8).stream().filter(entity -> entity.getType().isAlive()).forEach(entity -> ((LivingEntity) entity).damage(4.5, user.toPlayer()));

                        Lang.sendTo(user, Lang.WIZARD_POISON);
                }

                user.getCache().set("wizard-spell-cooldown", true);
                Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("wizard-spell-cooldown"), 250);

            } else {
                SpellSelector.create().open(user);
            }

        }

    }

}
