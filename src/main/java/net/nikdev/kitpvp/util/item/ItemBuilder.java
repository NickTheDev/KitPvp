package net.nikdev.kitpvp.util.item;

import net.nikdev.kitpvp.util.Chat;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Collection;

/**
 * Builder pattern implementation with a {@link ItemStack} result type.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class ItemBuilder {

    private final ItemStack item;

    /**
     * Creates a new item builder with the specified item.
     *
     * @param item Item to be used in this builder.
     */
    private ItemBuilder(ItemStack item) {
        this.item = item;
    }

    /**
     * Sets the name of the item in this builder to the specified name, and returns this builder.
     *
     * @param name Name of the item in this builder.
     * @return This builder.
     */
    public ItemBuilder name(String name) {
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Chat.color(name));
        item.setItemMeta(meta);

        return this;
    }

    /**
     * Sets the amount of the item in this builder to the specified amount, and returns this builder.
     *
     * @param amount Amount of the item in this builder.
     * @return This builder.
     */
    public ItemBuilder amount(int amount) {
        item.setAmount(amount);

        return this;
    }

    /**
     * Sets the material of the item in this builder to the specified material, and returns this builder.
     *
     * @param material Material of the item in this builder.
     * @return This builder.
     */
    public ItemBuilder material(Material material) {
        item.setType(material);

        return this;
    }

    /**
     * Adds the specified enchantment to the item in this builder, and returns this builder.
     *
     * @param enchantment Enchantment to add to the item in this builder.
     * @return This builder.
     */
    public ItemBuilder enchant(Enchantment enchantment, int level) {
        item.addUnsafeEnchantment(enchantment, level);

        return this;
    }

    /**
     * Sets the lore of the item in this builder to the specified lore, and returns this builder.
     *
     * @param lore Lore of the item in this builder.
     * @return This builder.
     */
    public ItemBuilder lore(Collection<String> lore) {
        ItemMeta meta = item.getItemMeta();

        meta.setLore(Chat.color(lore));
        item.setItemMeta(meta);

        return this;
    }

    /**
     * Sets the armor color of the item in this builder to the specified color.
     *
     * @param color Color of the item in this builder.
     * @return This builder.
     */
    public ItemBuilder armorColor(Color color) {
        try {
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

            meta.setColor(color);
            item.setItemMeta(meta);

        } catch(ClassCastException ignored) {}

        return this;
    }

    /**
     * Gets the item result of this builder.
     *
     * @return This builder's result.
     */
    public ItemStack build() {
        return item.clone();
    }

    /**
     * Gets a new item builder with the specified item material.
     *
     * @return New item builder with the specified material.
     */
    public static ItemBuilder builder(Material material) {
        return builder(material, (short) 0);
    }

    /**
     * Gets a new item builder with the specified item material and data.
     *
     * @return New item builder with the specified item material and data.
     */
    public static ItemBuilder builder(Material material, short data) {
        return builder(new ItemStack(material, 1, data));
    }

    /**
     * Gets a new item builder with the specified base item.
     *
     * @return New item builder with the specified base item.
     */
    public static ItemBuilder builder(ItemStack item) {
        if(item == null) {
            throw new IllegalArgumentException("Invalid arguments for a item builder.");
        }

        return new ItemBuilder(item);
    }

}
