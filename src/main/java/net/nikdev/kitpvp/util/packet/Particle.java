package net.nikdev.kitpvp.util.packet;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Location;

/**
 * Utility for sending a particle packet to a {@link User}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Particle {

    private final Location location;
    private final String type;

    /**
     * Creates a new title with the specified builder.
     *
     * @param builder Builder of this title.
     */
    private Particle(Builder builder) {
        location = builder.location;
        type = builder.type;
    }

    /**
     * Sends this particle packet to the specified target user.
     *
     * @param user User to send the title to.
     */
    @SuppressWarnings("unchecked")
    public void send(User user) {
        try {
            Object effect = Enum.valueOf((Class<Enum>) Packets.getNative("EnumParticle"), type.toUpperCase());

            Object packet = Packets.getNative("PacketPlayOutWorldParticles").getConstructor(Packets.getNative("EnumParticle"),
                    Boolean.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE, int[].class)
                    .newInstance(effect, true, (float) location.getX(), (float) location.getY(),
                            (float) location.getZ(), 0, 0, 0, 10, 0, null);

            Packets.send(user, packet);

        } catch(ReflectiveOperationException e) {
            KitPvp.get().getLogger().severe("An error occurred sending particle packet to user: " + user.getName());

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
     * Builder pattern implementation with a {@link Particle} result type.
     *
     * @author NickTheDev
     * @since 1.0
     */
    public static final class Builder {

        private Location location;
        private String type;

        /**
         * Builder, and therefore should only be called by parent class.
         */
        private Builder() {}

        /**
         * Sets the location of the particle in this builder to the specified location.
         *
         * @param location Location to set.
         * @return This builder.
         */
        public Builder location(Location location) {
            this.location = location;

            return this;
        }

        /**
         * Sets the type of the particle in this builder to the specified type.
         *
         * @param type Type to set.
         * @return This builder.
         */
        public Builder type(String type) {
            this.type = type;

            return this;
        }

        /**
         * Gets the title result of this builder.
         *
         * @return This builder's result.
         */
        public Particle build() {
            return new Particle(this);
        }

    }

}
