package net.nikdev.kitpvp.util.packet;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;

/**
 * Utility for sending a title packet to a {@link User}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Title {

    private final String title;
    private final int fadeIn, fadeOut, stay;

    /**
     * Creates a new title with the specified builder.
     *
     * @param builder Builder of this title.
     */
    private Title(Builder builder) {
        title = builder.title;
        fadeIn = builder.fadeIn;
        fadeOut = builder.fadeOut;
        stay = builder.stay;
    }

    /**
     * Gets the native Minecraft class with the specified name.
     *
     * @param name Name of the class.
     * @return Class with the specified name.
     * @throws ReflectiveOperationException Thrown if an error occurs getting the class.
     */
    private Class<?> getNative(String name) throws ReflectiveOperationException {
        return  Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
    }

    /**
     * Attempts to send the packet to the specified target user.
     *
     * @param user User to send the packet to.
     * @param packet Packet to send.
     * @throws ReflectiveOperationException Thrown if an error occurs sending the packet.
     */
    private void sendPacket(User user, Object packet) throws ReflectiveOperationException {
        Object handle = user.toPlayer().getClass().getMethod("getHandle").invoke(user.toPlayer());

        Object connection = handle.getClass().getField("playerConnection").get(handle);
        connection.getClass().getMethod("sendPacket", getNative("Packet")).invoke(connection, packet);
    }

    /**
     * Sends this title packet to the specified target user.
     *
     * @param user User to send the title to.
     */
    public void send(User user) {
        try {
            Object chat = getNative("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + Chat.color(title) + "\"}");

            Constructor<?> constructor = getNative("PacketPlayOutTitle").getConstructor(getNative("PacketPlayOutTitle").getDeclaredClasses()[0], getNative("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
            Object packet = constructor.newInstance(getNative("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chat, fadeIn, stay, fadeOut);

            sendPacket(user, packet);

        } catch(ReflectiveOperationException e) {
            KitPvp.get().getLogger().severe("An error occurred sending title packet to user: " + user.getName());

            e.printStackTrace();
        }

    }

    /**
     * Gets a new title builder.
     *
     * @return New builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder pattern implementation with a {@link Title} result type.
     *
     * @author NickTheDev
     * @since 1.0
     */
    public static final class Builder {

        private String title;
        private int fadeIn, fadeOut, stay;

        /**
         * Builder, and therefore should only be called by parent class.
         */
        private Builder() {}

        /**
         * Sets the title of the title in this builder to the specified title.
         *
         * @param title Title to set.
         * @return This builder.
         */
        public Builder title(String title) {
            this.title = title;

            return this;
        }

        /**
         * Sets the fade in time of the title in this builder to the specified fade in.
         *
         * @param fadeIn Fade in to set.
         * @return This builder.
         */
        public Builder fadeIn(int fadeIn) {
            this.fadeIn = fadeIn;

            return this;
        }

        /**
         * Sets the fade out time of the title in this builder to the specified fade out.
         *
         * @param fadeOut Fade out to set.
         * @return This builder.
         */
        public Builder fadeOut(int fadeOut) {
            this.fadeOut = fadeOut;

            return this;
        }

        /**
         * Sets the stay time of the title in this builder to the specified stay.
         *
         * @param stay Stay to set.
         * @return This builder.
         */
        public Builder stay(int stay) {
            this.stay = stay;

            return this;
        }

        /**
         * Gets the title result of this builder.
         *
         * @return This builder's result.
         */
        public Title build() {
            return new Title(this);
        }

    }

}
