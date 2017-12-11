package net.nikdev.kitpvp.util;

import org.bukkit.ChatColor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilities pertaining to chat related functions.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Chat {

    /**
     * Utility class, not to be instantiated.
     */
    private Chat() {}

    /**
     * Colors the specified text using Bukkit's chat color codes.
     *
     * @param text Text to color.
     * @return Colored text.
     */
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    /**
     * Removes all color codes from the specified text.
     *
     * @param text Text to plain.
     * @return Stripped text.
     */
    public static String plain(String text) {
        return ChatColor.stripColor(text);
    }

    /**
     * Colors the specified texts using Bukkit's chat color codes.
     *
     * @param texts Texts to color.
     * @return Colored texts.
     */
    public static List<String> color(Collection<String> texts) {
        return texts.stream().map(Chat::color).collect(Collectors.toList());
    }

}
