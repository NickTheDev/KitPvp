package net.nikdev.kitpvp.util.packet;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;

/**
 * Utility for sending a title packet to a {@link User}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class ActionBar {

    private final String text;

    /**
     * Creates a new title with the specified builder.
     *
     * @param builder Builder of this title.
     */
    private ActionBar(Builder builder) {
        text = builder.text;
    }

    /**
     * Sends this action bar packet to the specified target user.
     *
     * @param user User to send the title to.
     */
    public void send(User user) {
        try {
            Object message = Packets.getNative("ChatComponentText").getConstructor(String.class).newInstance(Chat.color(text));
            Object packet = Packets.getNative("PacketPlayOutChat").getConstructor(Packets.getNative("IChatBaseComponent"), Byte.TYPE).newInstance(message, (byte) 2);

            Packets.send(user, packet);

        } catch(ReflectiveOperationException e) {
            KitPvp.get().getLogger().severe("An error occurred sending action bar packet to user: " + user.getName());

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
     * Builder pattern implementation with a {@link ActionBar} result type.
     *
     * @author NickTheDev
     * @since 1.0
     */
    public static final class Builder {

        private String text;

        /**
         * Builder, and therefore should only be called by parent class.
         */
        private Builder() {}

        /**
         * Sets the title of the action bar in this builder to the specified title.
         *
         * @param text Title to set.
         * @return This builder.
         */
        public Builder text(String text) {
            this.text = text;

            return this;
        }

        /**
         * Gets the action bar result of this builder.
         *
         * @return This builder's result.
         */
        public ActionBar build() {
            return new ActionBar(this);
        }

    }

}
