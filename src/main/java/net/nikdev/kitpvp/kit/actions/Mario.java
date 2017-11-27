package net.nikdev.kitpvp.kit.actions;

import net.nikdev.kitpvp.kit.KitAction;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.ItemBuilder;
import net.nikdev.kitpvp.util.Skulls;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Action implementation for the Mario kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Mario implements KitAction {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/a0c2549a893726988f3428bef799875ba871688ae64eb0cfdc43f7d6e24c6c")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2), ItemBuilder.builder(Material.LEATHER_CHESTPLATE).armorColor(Color.BLUE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1), ItemBuilder.builder(Material.IRON_LEGGINGS), ItemBuilder.builder(Material.IRON_BOOTS)
                .enchant(Enchantment.PROTECTION_FALL, 1));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD).enchant(Enchantment.DAMAGE_ALL, 2));
        user.give(ItemBuilder.builder(Material.BROWN_MUSHROOM).name("&e&lJump Boost"));

        user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        // TODO Ability doesn't make any sense so gotta figure that out.
    }

}
