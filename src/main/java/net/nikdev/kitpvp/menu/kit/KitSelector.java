package net.nikdev.kitpvp.menu.kit;

import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.menu.ItemBuilder;
import net.nikdev.kitpvp.menu.Menu;
import net.nikdev.kitpvp.menu.MenuCallback;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Kit Selector menu which allows {@link User}s to select their kits.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class KitSelector implements MenuCallback {

    private static final MenuCallback CALLBACK = new KitSelector();

    @Override
    public void interact(User user, ItemStack item) {
        String id = Chat.strip(item.getItemMeta().getDisplayName());

        if(user.getStats().getKits().contains(id)) {
            user.toPlayer().closeInventory();
            Kit.get(id).get().apply(user);

        } else {
            user.toPlayer().closeInventory();
            Lang.sendTo(user, Keys.NOT_PLAYER);
        }

    }

    /**
     * Creates a new instance of a Kit Selector menu for the specified user.
     *
     * @param user User to create the menu for.
     * @return New menu.
     */
    public static Menu create(User user) {
        Menu.Builder builder = Menu.builder("&f&lKit Selector", 54).callback(CALLBACK);

        builder.item(ItemBuilder.builder(Material.ARROW).name("&c&lExit"));

        Kit.getKits().forEach(kit -> {
            if(user.getStats().getKits().contains(kit.getId())) {
                builder.item(ItemBuilder.builder(kit.getIcon(), kit.getIconData().orElse((short) 0)).name("&a&l" + kit.getName())
                        .lore(Arrays.asList("&8" + kit.getDescription(), "&a&l✔ You own this kit.")));

            } else {
                builder.item(ItemBuilder.builder(kit.getIcon(), kit.getIconData().orElse((short) 0)).name("&c&l" + kit.getName())
                        .lore(Arrays.asList("&8" + kit.getDescription(), "&a&l✖ You don't own this kit.")));
            }

        });

        return builder.build();
    }

}
