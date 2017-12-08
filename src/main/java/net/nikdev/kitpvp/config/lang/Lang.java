package net.nikdev.kitpvp.config.lang;

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
public enum Lang {

    /**
     * Component of the title sent to a player when they join.
     */
    JOIN_TITLE("server.join-title"),

    /**
     * Message sent to all players when a player joins the server. Placeholders are %NAME%.
     */
    PLAYER_JOIN("server.player-join"),

    /**
     * Message sent to all players when a player quits the server. Placeholders are %NAME%.
     */
    PLAYER_QUIT("server.player-quit"),

    /**
     * Message sent to all players when a player is killed by something. Placeholders are %NAME% and %CAUSE%.
     */
    PLAYER_DEATH("server.player-death"),

    /**
     * Component of the title sent to a player when they kill another player informing how many tokens they have received. Placeholders are %AMOUNT%.
     */
    DEATH_TOKEN_TITLE("server.death-token-title"),

    /**
     * Message sent to a player when they achieve a streak. Placeholders are %STREAK% and %AMOUNT%.
     */
    PLAYER_STREAK("server.player-streak"),

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
     * Message sent to a player when they use the modify argument with an unknown target player. Placeholders are %TARGET%.
     */
    UNKNOWN_TARGET("modify.unknown-target"),

    /**
     * Message sent to a player when they use the modify argument with an invalid amount. Placeholders are %AMOUNT%.
     */
    INVALID_AMOUNT("modify.invalid-amount"),

    /**
     * Message sent a player when they use the modify argument with an invalid operation. Placeholders are %OPERATION%.
     */
    UNKNOWN_MODIFY("modify.unknown-modify"),

    /**
     * Message sent to a command sender when they use the Token argument without any sub-arguments.
     */
    NO_TOKEN_ARGS("modify.no-token-args"),

    /**
     * Message sent to a command sender when they successfully update a player's tokens. Placeholders are %TARGET% and %AMOUNT%.
     */
    TOKENS_UPDATED("modify.tokens-updated"),

    /**
     * Message sent to the console when it attempts to check its personal token balance.
     */
    ATTEMPT_CONSOLE_PERSONAL("tokens.attempt-console-personal"),

    /**
     * Message sent to a player when they use the tokens argument without any sub-arguments, meaning they want to check their balance. Placeholders are %AMOUNT%.
     */
    PERSONAL_TOKEN_CHECK("tokens.personal-token-check"),

    /**
     * Message sent toa sender when they use the tokens argument to check another player's balance. Placeholders %TARGET% and %AMOUNT%.
     */
    TOKEN_CHECK("tokens.token-check"),

    /**
     * Message sent to a player when they use the set locations argument without having the region locations set.
     */
    LOCATIONS_NOT_SET("wand.locations-not-set"),

    /**
     * Message sent to a player when they use the set locations argument but their location is outside of the spawn region.
     */
    CANNOT_SPAWN_OUTSIDE("wand.cannot-spawn-outside"),

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
     * Message sent to a player when they successfully use the setlocations argument.
     */
    LOCATIONS_SET("wand.locations-set"),

    /**
     * Message sent to a player when they join the server but the spawn hasn't been set.
     */
    SPAWN_NOT_SET("wand.spawn-not-set"),

    /**
     * Message sent to a player when they attempt to use a kit ability without waiting for its cooldown to reset.
     */
    COOLDOWN("kits.cooldown"),

    /**
     * Message sent to a player when they attempt to select a kit without purchasing it beforehand.
     */
    NOT_PURCHASED("kits.not-purchased"),

    /**
     * Message sent to a player when they attempt to purchase a kit they already own.
     */
    ALREADY_PURCHASED("kits.already-purchased"),

    /**
     * Message sent to a player when they attempt to purchase a kit without sufficient tokens.
     */
    INSUFFICIENT_TOKENS("kits.insufficient-tokens"),

    /**
     * Message sent to a player when they successfully purchase a kit. Placeholders are %KIT%.
     */
    SUCCESSFUL_PURCHASE("kits.successful-purchase"),

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

    private static final Placeholder prefix = Placeholder.of("prefix", KitPvp.get().getLang().get("prefix"));
    private final String key;

    /**
     * Creates a new config key with the specified key.
     *
     * @param key Key of the config key.
     */
    Lang(String key) {
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
    public static String get(Lang key, Placeholder... holders) {
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
    public static void sendTo(Object receiver, Lang key, Placeholder... holders) {
        if(get(key).equals("")) {
            return;
        }

        if(receiver instanceof CommandSender) {
            ((CommandSender) receiver).sendMessage(get(key, holders));

        } else if(receiver instanceof User) {
            ((User) receiver).toPlayer().sendMessage(get(key, holders));
        }

    }

}
