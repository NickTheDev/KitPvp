package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.menu.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Action implementation for the Spiderman kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Spiderman implements KitCallback {

    private static final String NET_MINECRAFT = "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";

    @Override
    public void give(User user) {
        user.setArmor(ItemBuilder.builder(Material.LEATHER_HELMET).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2),
                ItemBuilder.builder(Material.IRON_CHESTPLATE), ItemBuilder.builder(Material.IRON_LEGGINGS),
                ItemBuilder.builder(Material.LEATHER_BOOTS).armorColor(Color.RED).enchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2));

        user.give(ItemBuilder.builder(Material.DIAMOND_SWORD));
        user.give(ItemBuilder.builder(Material.WEB).name("&e&lShoot Web").lore(Collections.singleton("&f&lRight click to activate.")));

        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        if(checkName(item,"&e&lShoot Web")) {
            if(user.getCache().contains("spiderman-web-cooldown")) {
                Lang.sendTo(user, Keys.COOLDOWN);

                return;
            }

            Fireball fake = user.toPlayer().launchProjectile(Fireball.class);

            fake.setDirection(user.toPlayer().getEyeLocation().getDirection().multiply(2));
            fake.setYield(0);
            fake.setIsIncendiary(false);

            injectInvisibility(fake);

            user.getCache().set("spiderman-web-cooldown", true);
            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("spiderman-web-cooldown"), 60);
        }

    }

    /**
     * Makes the specified fake fireball invisible to all players in its world.
     *
     * @param ball Ball to make invisible.
     */
    private void injectInvisibility(Fireball ball) {
        try {
            Object packet = Class.forName(NET_MINECRAFT + "PacketPlayOutEntityDestroy").getConstructor(Integer.TYPE).newInstance(ball.getEntityId());

            for(Player player : ball.getWorld().getPlayers()) {
                Object connection = player.getClass().getMethod("getHandle").invoke(player).getClass().getField("playerConnection").get(player);

                connection.getClass().getMethod("sendPacket", Class.forName(NET_MINECRAFT + "Packet")).invoke(connection, packet);
            }

        } catch(ReflectiveOperationException e) {
            e.printStackTrace();
        }

    }

}
