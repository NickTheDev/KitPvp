package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.Cooldowns;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import net.nikdev.kitpvp.util.item.Skulls;
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
public class Sonic implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/50381737d4da528823547251b145568d15253a87b11193c3daff6ae355775")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2), ItemBuilder.builder(Material.LEATHER_CHESTPLATE).armorColor(Color.BLUE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).enchant(Enchantment.DURABILITY, 3), ItemBuilder.builder(Material.CHAINMAIL_LEGGINGS),
                ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
                .enchant(Enchantment.DURABILITY, 3));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 1), ItemBuilder.builder(Material.INK_SACK, (short) 12).name("&e&lSpeed burst")
                .lore(Collections.singleton("&f&lClick to activate.")));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item, "Speed burst")) {
            if(Cooldowns.check(user, "sonic-speed")) {
                return;
            }

            user.toPlayer().removePotionEffect(PotionEffectType.SPEED);
            user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 69));

            Cooldowns.start(user, "sonic-speed", 400);

            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> {
                user.toPlayer().removePotionEffect(PotionEffectType.SPEED);

                user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
            }, 60);

        }

    }

}
