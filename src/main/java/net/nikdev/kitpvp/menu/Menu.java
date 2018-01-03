package net.nikdev.kitpvp.menu;

import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * Represents an inventory menu.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Menu {

    private final Inventory raw;
    private final Callback callback;

    /**
     * Creates a new menu with the specified builder.
     *
     * @param builder Builder of this menu.
     */
    private Menu(Builder builder) {
        raw = builder.inventory;
        callback = builder.callback;
    }

    /**
     * Gets the name of this menu.
     *
     * @return This menu's name.
     */
    public String getName() {
        return raw.getName();
    }

    /**
     * Gets the callback of this menu.
     *
     * @return This menu's callback.
     */
    public Optional<Callback> getCallback() {
        return Optional.ofNullable(callback);
    }

    /**
     * Opens this menu for the specified user.
     *
     * @param user User to open for.
     */
    public void open(User user) {
        user.getCache().set("menu", this);

        user.toPlayer().openInventory(raw);
    }

    /**
     * Gets a new menu builder with the specified name and amount of slots.
     *
     * @param name Name of the menu in the builder.
     * @param slots Slots of the menu in the builder.
     * @return New menu builder with the specified information.
     */
    public static Builder builder(String name, int slots) {
        if(name == null || slots % 9 != 0) {
            throw new IllegalArgumentException("Invalid arguments for a menu builder.");
        }

        return new Builder(name, slots);
    }

    /**
     * Builder pattern implementation with a {@link Inventory} result type.
     *
     * @author NickTheDev
     * @since 1.0
     */
    public static final class Builder {

        private final Inventory inventory;
        private Callback callback;

        /**
         * Creates a new menu builder with the specified information.
         *
         * @param name Name of the menu in this builder.
         * @param slots Slots of the menu in this builder.
         */
        private Builder(String name, int slots) {
            inventory = Bukkit.createInventory(null, slots, Chat.color(name));
        }

        /**
         * Adds the specified item to the menu of this builder.
         *
         * @param item Item to add.
         * @return This builder.
         */
        public Builder item(ItemBuilder item) {
            inventory.addItem(item.build());

            return this;
        }

        /**
         * Adds the specified item to the menu of this builder at the specified slot.
         *
         * @param item Item to add.
         * @param slot Slot of the item.
         * @return This builder.
         */
        public Builder item(ItemBuilder item, int slot) {
            inventory.setItem(slot, item.build());

            return this;
        }

        /**
         * Sets the callback of the menu in this builder to the specified callback.
         *
         * @param callback Callback to set.
         * @return This builder.
         */
        public Builder callback(Callback callback) {
            this.callback = callback;

            return this;
        }

        /**
         * Gets the menu result of this builder.
         *
         * @return This builder's result.
         */
        public Menu build() {
            return new Menu(this);
        }

    }

    /**
     * Represents a callback that will be notified when a {@link User} interacts with a {@link Menu}.
     *
     * @author NickTheDev
     * @since 1.0
     */
    public interface Callback {

        /**
         * Gets the if the item is the exit item and the callback should exit the menu.
         *
         * @param item Item to check.
         * @return If the callback should exit.
         */
        default boolean shouldExit(ItemStack item) {
            return item.getItemMeta().getDisplayName().equals(Chat.color(Config.get(Config.EXIT_ITEM_NAME)));
        }

        /**
         * Called when a user interacts with the menu this callback has been registered to.
         *
         * @param user User that interacted.
         * @param item Item that was interacted with.
         */
        void interact(User user, ItemStack item);

    }

}
