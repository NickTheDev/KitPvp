package net.nikdev.kitpvp.config;

import net.nikdev.kitpvp.KitPvp;
import org.bukkit.Material;

/**
 * Utility for accessing plugin config preferences.
 *
 * @author NickTheDev
 * @since 1.0
 */
public enum Config {

    /**
     * Database address.
     */
    DATABASE_ADDRESS("database.address"),

    /**
     * Database port.
     */
    DATABASE_PORT("database.port"),

    /**
     * Database name.
     */
    DATABASE_NAME("database.name"),

    /**
     * Database user.
     */
    DATABASE_USER("database.user"),

    /**
     * Database password.
     */
    DATABASE_PASSWORD("database.password"),

    /**
     * Whether or not rain is allowed on the server.
     */
    ALLOW_RAIN("world.allow-rain"),

    /**
     * Whether or not hunger is allowed on the server.
     */
    ALLOW_HUNGER("world.allow-hunger"),

    /**
     * Name of the exit item.
     */
    EXIT_ITEM_NAME("menu.exit-item.name"),

    /**
     * Material of the exit item.
     */
    EXIT_ITEM_MATERIAL("menu.exit-item.material"),

    /**
     * Amount of time a player is kept in spectator mode before respawning. This must be positive. The time unit is in seconds.
     */
    RESPAWN_TIME("player.respawn-time"),

    /**
     * Amount of health restored by mushroom soup in kits.
     */
    MUSHROOM_HEAL("player.mushroom-heal"),

    /**
     * Default award given to a player for killing another player.
     */
    DEFAULT_AWARD("player.default-award"),

    /**
     * Name of the kit selector item.
     */
    KIT_SELECTOR_NAME("spawn-item.kit-selector.name"),

    /**
     * Material of the kit selector item.
     */
    KIT_SELECTOR_MATERIAL("spawn-item.kit-selector.material"),

    /**
     * Name of the kit shop item.
     */
    KIT_SHOP_NAME("spawn-item.kit-shop.name"),

    /**
     * Material of the kit shop item.
     */
    KIT_SHOP_MATERIAL("spawn-item.kit-shop.material"),

    /**
     * Name of the previous kit item.
     */
    PREVIOUS_KIT_NAME("spawn-item.previous-kit.name"),

    /**
     * Material of the previous kit item.
     */
    PREVIOUS_KIT_MATERIAL("spawn-item.previous-kit.material"),

    /**
     * Title of the selector menu.
     */
    KIT_SELECTOR_TITLE("menu.kit-selector.title"),

    /**
     * Size of the selector menu, which must be bigger than the number of kits plus one (for the exit arrow). Must also be a multiple of 9.
     */
    KIT_SELECTOR_SIZE("menu.kit-selector.size"),

    /**
     * Description of kit icons who are owned by players.
     */
    KIT_OWNED_DESCRIPTION("menu.kit-selector.kit-owned-description"),

    /**
     * Description of kit icons who are not owned by players.
     */
    KIT_WANTED_DESCRIPTION("menu.kit-selector.kit-wanted-description"),

    /**
     * Title of the shop menu.
     */
    KIT_SHOP_TITLE("menu.kit-shop.title"),

    /**
     * Size of the shop menu.
     */
    KIT_SHOP_SIZE("menu.kit-shop.size"),

    /**
     * Description of kit icons who are not owned by players but can be purchased.
     */
    KIT_PURCHASE_DESCRIPTION("menu.kit-shop.kit-purchase-description"),

    /**
     * Description of kit icons detailing their cost. Placeholders are %COST%.
     */
    KIT_COST_DESCRIPTION("menu.kit-shop.kit-cost-description"),

    /**
     * Title of the spell selector menu.
     */
    SPELL_SELECTOR_TITLE("menu.spell-selector.title"),

    /**
     * Size of the spell selector menu.
     */
    SPELL_SELECTOR_SIZE("menu.spell-selector.size");

    private final String key;

    /**
     * Creates a new config key with the specified key.
     *
     * @param key Key of the config key.
     */
    Config(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * Gets the string from {@link KitPvp}'s config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the config.
     */
    public static String get(Config key) {
        return KitPvp.get().getConfig().getString(key.toString(), "");
    }

    /**
     * Gets the material from {@link KitPvp}'s config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the config.
     */
    public static Material getMaterial(Config key) {
        Material material = Material.matchMaterial(get(key));

        return material != null ? material : Material.AIR;
    }

    /**
     * Gets the integer from {@link KitPvp}'s config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the config.
     */
    public static int getInt(Config key) {
        return KitPvp.get().getConfig().getInt(key.toString(), 0);
    }

    /**
     * Gets the boolean from {@link KitPvp}'s config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the config.
     */
    public static boolean getBool(Config key) {
        return KitPvp.get().getConfig().getBoolean(key.toString(), false);
    }

}
