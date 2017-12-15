package net.nikdev.kitpvp.menu.kit;

import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.kit.callbacks.Wizard;
import net.nikdev.kitpvp.menu.Menu;
import net.nikdev.kitpvp.menu.MenuCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Kit Shop menu which allows {@link Wizard}s to set their current spell.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class SpellSelector implements MenuCallback {

    private static final Menu menu = Menu.builder(Config.get(Config.SPELL_SELECTOR_TITLE), Config.getInt(Config.SPELL_SELECTOR_SIZE)).item(ItemBuilder.builder(Material.BOOK_AND_QUILL)
                .name("&c&lHeal")).item(ItemBuilder.builder(Material.BOOK_AND_QUILL).name("&2&lPoison")).callback(new SpellSelector()).build();

    @Override
    public void interact(User user, ItemStack item) {
        switch(Chat.plain(item.getItemMeta().getDisplayName())) {
            case "Heal":
                user.getCache().set("wizard-spell", "heal");
                user.toPlayer().closeInventory();

                break;

            case "Poison":
                user.getCache().set("wizard-spell", "poison");
                user.toPlayer().closeInventory();
        }

    }

    /**
     * Creates a new instance of a Kit Shop menu.
     *
     * @return New menu.
     */
    public static Menu create() {
        return menu;
    }

}
