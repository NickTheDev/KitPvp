package net.nikdev.kitpvp.kit.actions;

import net.nikdev.kitpvp.kit.KitAction;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.ItemBuilder;
import net.nikdev.kitpvp.util.Skulls;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collections;

/**
 * Action implementation for the Donkey Kong kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class DonkeyKong implements KitAction {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/911be759233c74734b211ece555d585a229a399d5a92b3614ab47c046d785")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3), ItemBuilder.builder(Material.LEATHER_CHESTPLATE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3), ItemBuilder.builder(Material.LEATHER_LEGGINGS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3), ItemBuilder.builder(Material.LEATHER_BOOTS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3));

        user.give(ItemBuilder.builder(Material.GOLD_BLOCK).name("&e&lPickup Player").lore(Collections.singleton("&f&lRight click a player to activate.")));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HARM, Integer.MAX_VALUE, 2));
        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(item.getType().equals(Material.GOLD_BLOCK) && user.toPlayer().getPassenger() != null) {
            Player player = (Player) user.toPlayer().getPassenger();

            user.toPlayer().eject();
            player.setVelocity(user.toPlayer().getEyeLocation().getDirection());
        }

    }

}
