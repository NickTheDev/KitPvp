package net.nikdev.kitpvp.lang;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import org.bukkit.command.CommandSender;

/**
 * Utility for accessing plugin language preferences.
 *
 * @author NickTheDev
 * @since 1.0
 */
public enum LangKeys {

    /**
     * Message sent to all players when a player joins the server. Placeholders are %NAME%.
     */
    PLAYER_JOIN("server.player-join"),

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
     * Message sent to a command sender when an error occurs executing a command.
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
    SPAWN_NOT_SET("wand.spawn-not-set");

    private static final Placeholder prefix = Placeholder.of("prefix", KitPvp.get().getLang().get("prefix"));
    private final String key;

    /**
     * Creates a new config key with the specified key.
     *
     * @param key Key of the config key.
     */
    LangKeys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * Gets the string from {@link KitPvp}'s language config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @param holders Optional placeholders to apply to the message.
     * @return Value from the language config.
     */
    public static String get(LangKeys key, Placeholder... holders) {
        String message = prefix.apply(KitPvp.get().getLang().get(key.toString()));

        for(Placeholder holder : holders) {
            message = holder.apply(message);
        }

        return Chat.color(message);
    }

    /**
     * Sends the message associated with the key to the specified receiver.
     *
     * @param receiver Receiver of the message.
     * @param holders Placeholders of the message.
     * @param key Key of the message.
     */
    public static void sendTo(Object receiver, LangKeys key, Placeholder... holders) {
        if(get(key).equals("")) {
            return;
        }

        if(receiver instanceof CommandSender) {
            ((CommandSender) receiver).sendMessage(get(key, holders));

        } else if(receiver instanceof User) {
            ((User) receiver).toPlayer().sendMessage(get(key, holders));
        }

    }

    /**
     * Represents a text placeholder. The format of an id is %ID%, such as %NAME%.
     *
     * @author NickTheDev
     * @since 1.0
     */
    public static final class Placeholder {

        private final String id, value;

        /**
         * Creates a new placeholder with the specified information.
         *
         * @param id Id of this placeholder.
         * @param value Value of this placeholder.
         */
        private Placeholder(String id, String value) {
            this.id = id;
            this.value = value;
        }

        /**
         * Gets the id of this placeholder.
         *
         * @return This placeholder's id.
         */
        public String getId() {
            return id;
        }

        /**
         * Gets the value of this placeholder.
         *
         * @return This placeholder's value.
         */
        public String getValue() {
            return value;
        }

        /**
         * Applies this placeholder to the specified text and returns the modified text.
         *
         * @param text Text to modify.
         * @return Modified text with the placeholder value.
         */
        public String apply(String text) {
            return text.replaceAll(id, value);
        }

        /**
         * Utility to easily create a new placeholder. The id will be put into the correct format if
         * it is not already in it.
         *
         * @param id Id of the placeholder.
         * @param value Value of the placeholder.
         * @return New placeholder with the specified information.
         */
        public static Placeholder of(String id, Object value) {
            return new Placeholder(id.startsWith("%") ? id : "%" + id.toUpperCase() + "%", value.toString());
        }

    }

}
