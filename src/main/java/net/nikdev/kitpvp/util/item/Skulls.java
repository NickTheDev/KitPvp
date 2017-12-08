package net.nikdev.kitpvp.util.item;

import com.google.common.collect.ForwardingMultimap;
import net.nikdev.kitpvp.KitPvp;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

/**
 * Utilities for creating custom player skulls.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Skulls {

    private static final String AUTH_LIB = "com.mojang.authlib.";

    /**
     * Utility class, not to be instantiated.
     */
    private Skulls() {}

    /**
     * Creates a new game profile with a random unique id.
     *
     * @return New game profile.
     * @throws ReflectiveOperationException Thrown if an error occurs instantiated the class.
     */
    private static Object randomProfile() throws ReflectiveOperationException {
        return Class.forName(AUTH_LIB + "GameProfile").getConstructor(UUID.class, String.class).newInstance(UUID.randomUUID(), null);
    }

    /**
     * Injects the specified skin url into the property map.
     *
     * @param properties Property map to inject into.
     * @param url Skin url to inject.
     * @throws ReflectiveOperationException Thrown if an error occurs injecting the skin.
     */
    private static void injectProperty(ForwardingMultimap<String, Object> properties, String url) throws ReflectiveOperationException {
        byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        Class<?> propertyClass = Class.forName(AUTH_LIB + "properties.Property");

        Object property = propertyClass.getConstructor(String.class, String.class).newInstance("textures", new String(data));

        properties.put("textures", property);
    }

    /**
     * Injects the specified profile into a skull to create a custom texture effect.
     *
     * @param profile Profile to inject.
     * @return Injected item stack.
     * @throws ReflectiveOperationException Thrown if an error occurs injecting the profile.
     */
    private static ItemStack injectSkull(Object profile) throws ReflectiveOperationException {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        ItemMeta meta = head.getItemMeta();
        Field field = meta.getClass().getDeclaredField("profile");

        field.setAccessible(true);
        field.set(meta, profile);

        head.setItemMeta(meta);

        return head;
    }

    /**
     * Return a skull that has a custom texture specified by url.
     *
     * @param url skin url Url of the player head.
     * @return Item representation of the player head.
     */
    @SuppressWarnings("unchecked")
    public static ItemBuilder createSkull(String url) {
        try {
            Object profile = randomProfile();
            ForwardingMultimap<String, Object> properties = (ForwardingMultimap<String, Object>) profile.getClass().getMethod("getProperties").invoke(profile);

            injectProperty(properties, url);

            return ItemBuilder.builder(injectSkull(profile));

        } catch(ReflectiveOperationException e) {
            KitPvp.get().getLogger().severe("An error occurred creating custom skull for url: " + url);

            e.printStackTrace();
        }

        return ItemBuilder.builder(Material.SKULL_ITEM);
    }

}
