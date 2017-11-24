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
     * KitPvp's prefix.
     */
    PREFIX("prefix"),

    /**
     * Message sent when a command sender doesn't specify any arguments.
     */
    NO_ARGS("command.no-args"),

    /**
     * Message sent to a player when they use the wand argument.
     */
    WAND_GIVEN("command.wand-given"),

    /**
     * Message sent when a command sender uses the help argument.
     */
    HELP("command.help"),

    /**
     * Message sent for each argument when a command sender uses the help argument.
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
    ARG_ERROR("command.arg-error");

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
     * Gets the prefix of KitPvp.
     *
     * @return KitPvp's prefix.
     */
    public static String getPrefix() {
        return Chat.color(KitPvp.get().getLang().get(PREFIX.toString()));
    }

    /**
     * Gets the string from {@link KitPvp}'s language config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the language config.
     */
    public static String get(LangKeys key) {
        return Chat.color(KitPvp.get().getLang().get(key.toString()).replaceAll("%PREFIX%", getPrefix()));
    }

    public static void sendTo(Object receiver, LangKeys key) {
        if(receiver instanceof CommandSender) {
            ((CommandSender) receiver).sendMessage(get(key));

        } else if(receiver instanceof User) {
            ((User) receiver).toPlayer().sendMessage(get(key));
        }

    }

}
