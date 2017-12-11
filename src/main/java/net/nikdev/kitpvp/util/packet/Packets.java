package net.nikdev.kitpvp.util.packet;

import net.nikdev.kitpvp.user.User;
import org.bukkit.Bukkit;

/**
 * Utilities for sending packets.
 *
 * @author NickTheDev
 * @since 1.0
 */
final class Packets {

    /**
     * Utility class, not to be instantiated.
     */
    private Packets() {}

    /**
     * Gets the native Minecraft class with the specified name.
     *
     * @param name Name of the class.
     * @return Class with the specified name.
     * @throws ReflectiveOperationException Thrown if an error occurs getting the class.
     */
    public static Class<?> getNative(String name) throws ReflectiveOperationException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
    }

    /**
     * Attempts to send the packet to the specified target user.
     *
     * @param user User to send the packet to.
     * @param packet Packet to send.
     * @throws ReflectiveOperationException Thrown if an error occurs sending the packet.
     */
    public static void send(User user, Object packet) throws ReflectiveOperationException {
        Object handle = user.toPlayer().getClass().getMethod("getHandle").invoke(user.toPlayer());

        Object connection = handle.getClass().getField("playerConnection").get(handle);
        connection.getClass().getMethod("sendPacket", getNative("Packet")).invoke(connection, packet);
    }

}
