package net.nikdev.kitpvp.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Functional builder implementation with a {@link ItemStack} result type.
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
    ItemBuilder(ItemStack item) {
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
     * Sets the material data of the item in this builder to the specified data, and returns this builder.
     *
     * @param data Material data of the item in this builder.
     * @return This builder.
     */
    public ItemBuilder data(MaterialData data) {
        item.setData(data);

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

        meta.setLore(new ArrayList<>(Chat.color(lore)));
        item.setItemMeta(meta);

        return this;
    }

    /**
     * Gets the item stack built representation of this builder.
     *
     * @return Built item stack.
     */
    public ItemStack build() {
        return item.clone();
    }

    /**
     * Gets a new empty item builder with Material.AIR as the item's material.
     *
     * @return New empty item builder.
     */
    public static ItemBuilder builder() {
        return new ItemBuilder(new ItemStack(Material.AIR));
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
        return new ItemBuilder(item);
    }

}
