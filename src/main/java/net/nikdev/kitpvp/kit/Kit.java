package net.nikdev.kitpvp.kit;

import net.nikdev.kitpvp.user.User;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents {@link User} kits, which are the backbone of KitPvp.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Kit {

    private static List<Kit> kits = new ArrayList<>();

    private final String id, name, description;

    private final Material icon;
    private final int cost;
    private final KitAction action;

    /**
     * Creates a new kit with the specified information.
     *
     * @param id Id of this kit.
     * @param name Name of this kit.
     * @param description Description of this kit.
     * @param icon Description of this kit.
     * @param cost Cost of this kit.
     * @param action Action of this kit.
     */
    Kit(String id, String name, String description, Material icon, int cost, KitAction action) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.cost = cost;
        this.action = action;
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
     * Gets this cost of this kit.
     *
     * @return This kit's cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Gets this action of this kit.
     *
     * @return This kit's action.
     */
    public KitAction getAction() {
        return action;
    }

    /**
     * Gets all loaded kits.
     *
     * @return Loaded kits.
     */
    public static List<Kit> getKits() {
        return kits;
    }

}
