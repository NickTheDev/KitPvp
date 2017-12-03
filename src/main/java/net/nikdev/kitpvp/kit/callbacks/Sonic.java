package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.menu.ItemBuilder;
import net.nikdev.kitpvp.util.Skulls;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

/**
 * Action implementation for the Snowman kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Sonic implements KitCallback {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/50381737d4da528823547251b145568d15253a87b11193c3daff6ae355775")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2), ItemBuilder.builder(Material.LEATHER_CHESTPLATE).armorColor(Color.BLUE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).enchant(Enchantment.DURABILITY, 3), ItemBuilder.builder(Material.CHAINMAIL_LEGGINGS),
                ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .enchant(Enchantment.DURABILITY, 3));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 1));
        user.give(ItemBuilder.builder(Material.INK_SACK, (short) 12).name("&e&lSpeed burst").lore(Collections.singleton("&f&lClick to activate.")));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item, "&e&lSpeed burst")) {
            if(user.getCache().contains("sonic-speed-cooldown")) {
                Lang.sendTo(user, Keys.COOLDOWN);

                return;
            }

            user.toPlayer().removePotionEffect(PotionEffectType.SPEED);
            user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 6));

            user.getCache().set("sonic-speed-cooldown", true);

            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> {
                user.getCache().remove("sonic-speed-cooldown");

                user.toPlayer().removePotionEffect(PotionEffectType.SPEED);
                user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
            }, 400);

        }

    }

}