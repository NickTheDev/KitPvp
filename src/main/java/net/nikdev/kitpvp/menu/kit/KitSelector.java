package net.nikdev.kitpvp.menu.kit;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import net.nikdev.kitpvp.menu.Menu;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Kit Selector menu which allows {@link User}s to select their kits.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class KitSelector implements Menu.Callback {

    private static final Menu.Callback callback = new KitSelector();

    @Override
    public void interact(User user, ItemStack item) {
        if(shouldExit(item)) {
            user.toPlayer().closeInventory();

            return;
        }

        Kit kit = Kit.getByName(Chat.plain(item.getItemMeta().getDisplayName())).get();

        if(user.getStats().getKits().stream().anyMatch(id -> kit.getId().equals(id))) {
            user.toPlayer().closeInventory();
            kit.apply(user);

        } else {
            user.toPlayer().closeInventory();
            Lang.sendTo(user, Lang.NOT_PURCHASED);
        }

    }

    /**
     * Creates a new instance of a Kit Selector menu for the specified user.
     *
     * @param user User to create the menu for.
     * @return New menu.
     */
    public static Menu create(User user) {
        Menu.Builder builder = Menu.builder(Config.get(Config.KIT_SELECTOR_TITLE), Config.getInt(Config.KIT_SELECTOR_SIZE)).callback(callback);

        builder.item(ItemBuilder.builder(Config.getMaterial(Config.EXIT_ITEM_MATERIAL)).name(Config.get(Config.EXIT_ITEM_NAME)), Config.getInt(Config.KIT_SELECTOR_SIZE) - 1);

        KitPvp.get().getKitManager().getKits().forEach(kit -> {
            if(user.getStats().getKits().contains(kit.getId())) {
                builder.item(ItemBuilder.builder(kit.getIcon(), kit.getIconData().orElse((short) 0)).name("&a&l" + kit.getName())
                        .lore(Arrays.asList("&8" + kit.getDescription(), " ", Config.get(Config.KIT_OWNED_DESCRIPTION))));

            } else {
                builder.item(ItemBuilder.builder(kit.getIcon(), kit.getIconData().orElse((short) 0)).name("&c&l" + kit.getName())
                        .lore(Arrays.asList("&8" + kit.getDescription(), " ", Config.get(Config.KIT_WANTED_DESCRIPTION))));
            }

        });

        return builder.build();
    }

}
