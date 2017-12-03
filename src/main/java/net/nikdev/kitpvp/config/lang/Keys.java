package net.nikdev.kitpvp.config.lang;

/**
 * Represents the keys of various language message values.
 *
 * @author NickTheDev
 * @since 1.0
 */
public enum Keys {

    /**
     * Message sent to all players when a player joins the server. Placeholders are %NAME%.
     */
    PLAYER_JOIN("server.player-join"),

    /**
     * Message sent to all players when a player quits the server. Placeholders are %NAME%.
     */
    PLAYER_QUIT("server.player-quit"),

    /**
     * Message sent to a player when they attempt to leave spawn without selecting a kit.
     */
    NO_KIT_SELECTED("server.no-kit-selected"),

    /**
     * Message sent when a command sender doesn't specify any arguments.
     */
    NO_ARGS("command.no-args"),

    /**
     * Message sent when a command sender uses the help argument.
     */
    HELP("command.help"),

    /**
     * Message sent for each argument when a command sender uses the help argument. Placeholders are %NAME% and %HELP%.
     */
    HELP_TEMPLATE("command.help-template"),

    /**
     * Message sent to a command sender when they specify and unknown argument.
     */
    UNKNOWN_ARGS("command.unknown-arg"),

    /**
     * Message sent to the console when it tries to use a player only command.
     */
    NOT_PLAYER("command.not-player"),

    /**
     * Message sent to a command sender when they do not have a permission.
     */
    NO_PERMISSION("command.no-permission"),

    /**
     * Message sent to a command sender when an error occurs executing a command. Placeholders are %ERROR%.
     */
    ARG_ERROR("command.arg-error"),

    /**
     * Message sent to a player when they use the wand argument.
     */
    WAND_GIVEN("wand.wand-given"),

    /**
     * Message sent to a player when they right click the Location Wand and set the first corner of the spawn region.
     */
    FIRST_LOCATION_SET("wand.first-location-set"),

    /**
     * Message sent to a player when they left click the Location Wand and set the second corner of the spawn region.
     */
    SECOND_LOCATION_SET("wand.second-location-set"),

    /**
     * Message sent to a player when they attempt to use the setlocations argument without first using the Location Wand.
     */
    MUST_SET_LOCATIONS("wand.must-set-locations"),

    /**
     * Message sent to a player when they successfully use the setlocations argument.
     */
    LOCATIONS_SET("wand.locations-set"),

    /**
     * Message sent to a player when they use the setlocations argument but the spawn is outside of the region.
     */
    SPAWN_OUTSIDE_ERROR("wand.spawn-outside-error"),

    /**
     * Message sent to a player when they join the server but the spawn hasn't been set.
     */
    SPAWN_NOT_SET("wand.spawn-not-set"),

    /**
     * Message sent to a player when they attempt to use a kit ability without waiting for its cooldown to reset.
     */
    COOLDOWN("kit.cooldown"),

    /**
     * Message sent to a player when they attempt to select a kit without purchasing it beforehand.
     */
    NOT_PURCHASED("kit.not-purchased"),

    /**
     * Message sent to a player when they have been hooked by someone with the fisherman kit. Placeholders are %NAME%.
     */
    FISHERMAN_HOOKED("kits.fisherman.hooked"),

    /**
     * Message sent to a player with the medic kit when they heal themselves.
     */
    MEDIC_PERSONAL_HEAL("kits.medic.personal-heal"),

    /**
     * Message sent to a player with the medic kit when they heal others around them in a 5 block radius.
     */
    MEDIC_GROUP_HEAL("kits.medic.group-heal"),

    /**
     * Message sent to a player with the donkey kong kit when they pickup another player. Placeholders are %NAME%.
     */
    DONKEY_KONG_PICKUP("kits.donkeykong.pickup");

    private final String key;

    /**
     * Creates a new config key with the specified key.
     *
     * @param key Key of the config key.
     */
    Keys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

}
