package net.nikdev.kitpvp.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a cube of blocks defined by 6 coordinates and a world. Adapted from DarkBlade12's original
 * version.
 *
 * @author NickTheDev
 * @since 1.0
 */
@SerializableAs("Cuboid")
public final class Cuboid implements ConfigurationSerializable {

    private final int x1, y1, z1, x2, y2, z2;
    private final String world;

    /**
     * Creates a new cuboid with the specified locations.
     *
     * @param first First location of this cuboid.
     * @param second Second location of this cuboid.
     */
    public Cuboid(Location first, Location second)  {
        this(first.getWorld().getName(), Math.min(first.getBlockX(), second.getBlockX()), Math.max(first.getBlockX(), second.getBlockX()),
                Math.min(first.getBlockY(), second.getBlockY()), Math.max(first.getBlockY(), second.getBlockY()),
                Math.min(first.getBlockZ(), second.getBlockZ()), Math.max(first.getBlockZ(), second.getBlockZ()));
    }

    /**
     * Creates a new cuboid with the specified coordinates and world.
     *
     * @param world World of this cuboid.
     * @param x1 X-1 of this cuboid.
     * @param x2 X-2 of this cuboid.
     * @param y1 Y-1 of this cuboid.
     * @param y2 Y-2 of this cuboid.
     * @param z1 Z-1 of this cuboid.
     * @param z2 Z-2 of this cuboid.
     */
    private Cuboid(String world, int x1, int x2, int y1, int y2, int z1, int z2) {
        this.world = world;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
    }

    /**
     * Gets if the specified original integer is between the minimum and maximum bounds.
     *
     * @param original Original integer to check.
     * @param min Minimum bound.
     * @param max Maximum bound.
     * @return If the integer is between the bounds.
     */
    private boolean bounds(int original, int min, int max) {
        return original >= min && original <= max;
    }

    /**
     * Gets is the specified location is isInside this cuboid.
     *
     * @param location Location to check.
     * @return If the location is isInside this cuboid.
     */
    public boolean isInside(Location location) {
        return location.getWorld().getName().equals(world) && bounds(location.getBlockX(), x1, x2) &&
                bounds(location.getBlockY(), y1, y2) && bounds(location.getBlockZ(), z1, z2);
    }

    /**
     * Gets the width of this cuboid.
     *
     * @return This cuboid's width.
     */
    public int getWidth() {
        return x2 - x1 + 1;
    }

    /**
     * Gets the height of this cuboid.
     *
     * @return This cuboid's height.
     */
    public int getHeight() {
        return y2 - y1 + 1;
    }

    /**
     * Gets the length of this cuboid.
     *
     * @return This cuboid's length.
     */
    public int getLength() {
        return z2 - z1 + 1;
    }

    /**
     * Gets the volume of this cuboid.
     *
     * @return This cuboid's volume.
     */
    public int getVolume() {
        return getWidth() * getHeight() * getLength();
    }

    /**
     * Gets the world this cuboid is located in.
     *
     * @return This cuboid's world.
     */
    public World getWorld() {
        return Bukkit.getWorld(world);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> locations = new HashMap<>();

        locations.put("world", world);
        locations.put("x1", x1);
        locations.put("x2", x2);
        locations.put("y1", y1);
        locations.put("y2", y2);
        locations.put("z1", z1);
        locations.put("z2", z2);

        return locations;
    }

    /**
     * Deserializes a Cuboid from information found in the map. Required field from {@link ConfigurationSerializable}.
     *
     * @param map Map to pull information from.
     * @return New cuboid with information from the map.
     */
    public static Cuboid deserialize(Map<String, Object> map) {
        return new Cuboid(map.get("world").toString(), (int) map.get("x1"), (int) map.get("x2"), (int) map.get("y1"), (int) map.get("y2"),
            (int) map.get("z1"), (int) map.get("z2"));
    }

}