package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.KitCallback;
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

/**
 * Action implementation for the Luigi kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Luigi implements KitCallback {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/a557187a729e21d1e6b3cdf083aad2a646efaca2f489dac1b2fa324a4a7d9")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2), ItemBuilder.builder(Material.LEATHER_CHESTPLATE).armorColor(Color.AQUA)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1), ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.LEATHER_BOOTS));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 2), ItemBuilder.builder(Material.BROWN_MUSHROOM).name("&e&lJump Boost"));
        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item, "Jump Boost")) {
            if(user.getCache().contains("luigi-jump-cooldown")) {
                Lang.sendTo(user, Lang.COOLDOWN);

                return;
            }

            user.toPlayer().setVelocity(user.toPlayer().getLocation().add(0,1.1,0).toVector().subtract(user.toPlayer().getLocation().toVector()));

            user.getCache().set("no-fall", true);
            user.getCache().set("luigi-jump-cooldown", true);
            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("luigi-jump-cooldown"), 200);
        }

    }

}
