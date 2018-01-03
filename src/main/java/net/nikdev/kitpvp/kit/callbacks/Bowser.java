package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.Cooldowns;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import net.nikdev.kitpvp.util.item.Skulls;
import net.nikdev.kitpvp.util.packet.Particle;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Action implementation for the Bowser kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Bowser implements Kit.Callback {

    @Override
    public void give(User user) {
        user.setArmor(Skulls.createSkull("http://textures.minecraft.net/texture/843059ebb8a5a71e20c0aa752b3ea418afd08aaf21c633744f9c5de15c699bce")
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1), ItemBuilder.builder(Material.LEATHER_CHESTPLATE)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1).armorColor(Color.YELLOW), ItemBuilder.builder(Material.LEATHER_LEGGINGS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1).armorColor(Color.YELLOW), ItemBuilder.builder(Material.LEATHER_BOOTS)
                .enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).enchant(Enchantment.THORNS, 1).armorColor(Color.YELLOW));

        user.give(ItemBuilder.builder(Material.BLAZE_POWDER).name("&e&lBreathe Fire"));

        fillSoup(user);
    }


    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if (checkName(item, "Breathe Fire")) {
            if(Cooldowns.check(user, "bowser-fire")) {
                return;
            }

            createBeam(user);
            Cooldowns.start(user, "bowser-fire", 40);
        }

    }

    /**
     * Creates a new fire beam in the direction the user is facing.
     *
     * @param user User to create the beam for.
     */
    private void createBeam(User user) {
        Location location = user.toPlayer().getLocation();
        Vector direction = location.getDirection();

        for(double distance = 0; distance < 16; distance++) {
            location.add(direction);
            location.add(0, 1.5, 0);

            Particle particle = Particle.builder().location(location).type("lava").build();
            location.getWorld().getPlayers().forEach(player -> particle.send(User.get(player.getUniqueId()).get()));

            if (location.getBlock().getType().isSolid()) {
                return;
            }

            for (Entity entity : location.getChunk().getEntities()) {
                if (entity != user.toPlayer() && entity.getType().isAlive() && entity.getLocation().distance(location) < 1.5) {
                    ((Damageable) entity).damage(3.5, user.toPlayer());

                    return;
                }
            }

            location.subtract(0, 1.5, 0);
        }

    }

}
