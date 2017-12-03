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
public final class Lang {

    private static final Placeholder prefix = Placeholder.of("prefix", KitPvp.get().getLang().get("prefix"));

    /**
     * Gets the string from {@link KitPvp}'s language config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @param holders Optional placeholders to apply to the message.
     * @return Value from the language config.
     */
    public static String get(Keys key, Placeholder... holders) {
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
    public static void sendTo(Object receiver, Keys key, Placeholder... holders) {
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
