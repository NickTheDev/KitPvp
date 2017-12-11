package net.nikdev.kitpvp.kit;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Material;

import java.util.*;

/**
 * Represents {@link User} kits, which are the backbone of KitPvp.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Kit {

    private final String id, name, description;

    // Stored as the wrapper and not primitive to support nullability.
    private final Short iconData;
    private final Material icon;
    private final int cost;
    private final KitCallback callback;

    /**
     * Creates a new kit with the specified information.
     *
     * @param id Id of this kit.
     * @param name Name of this kit.
     * @param description Description of this kit.
     * @param icon Description of this kit.
     * @param iconData Icon data of this kit.
     * @param cost Cost of this kit.
     * @param callback Callback of this kit.
     */
    public Kit(String id, String name, String description, Material icon, Short iconData, int cost, KitCallback callback) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.iconData = iconData;
        this.cost = cost;
        this.callback = callback;
    }

    /**
     * Gets this id of this kit.
     *
     * @return This kit's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets this name of this kit.
     *
     * @return This kit's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets this description of this kit.
     *
     * @return This kit's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets this icon of this kit.
     *
     * @return This kit's icon.
     */
    public Material getIcon() {
        return icon;
    }

    /**
     * Gets the optional data of the icon of this kit.
     *
     * @return This kit's icon data.
     */
    public Optional<Short> getIconData() {
        return Optional.ofNullable(iconData);
    }

    /**
     * Gets this cost of this kit.
     *
     * @return This kit's cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets this callback of this kit.
     *
     * @return This kit's callback.
     */
    public KitCallback getCallback() {
        return callback;
    }

    /**
     * Gives this kit to the specified user.
     *
     * @param user User to give to.
     */
    public void apply(User user) {
        user.clean();
        user.getCache().set("previous-kit", getId());
        user.getCache().set("kit", this);

        getCallback().give(user);
    }

    /**
     * Shorthand for KitManager#get().
     *
     * @param id Id of the kit.
     * @return Kit with the id.
     */
    public static Optional<Kit> get(String id) {
        return KitPvp.get().getKitManager().get(id);
    }

    /**
     * Shorthand for KitManager#getByName().
     *
     * @param name Name of the kit.
     * @return Kit with the name.
     */
    public static Optional<Kit> getByName(String name) {
        return KitPvp.get().getKitManager().getByName(name);
    }

}
