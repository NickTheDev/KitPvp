package net.nikdev.kitpvp.kit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Manages the registry of {@link Kit}s and holds functions for acting on them.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class KitManager {

    private final Map<String, Kit> kits = new HashMap<>();

    /**
     * Utility for checking if a collection of kit identifiers contains the kit with the specified name.
     *
     * @param name Name of the kit to check.
     * @return Kit with the name.
     */
    public Optional<Kit> getByName(String name) {
        return getKits().stream().filter(kit -> kit.getName().equals(name)).findFirst();
    }

    /**
     * Gets the kit with the specified id.
     *
     * @param id Id of the kit.
     * @return Kit with the id.
     */
    public Optional<Kit> get(String id) {
        return Optional.ofNullable(kits.get(id));
    }

    /**
     * Gets all currently registered kits.
     *
     * @return Registered kits.
     */
    public Collection<Kit> getKits() {
        return kits.values();
    }

    /**
     * Registers the specified kit if it is not already registered.
     *
     * @param kit Kit to register.
     */
    public void register(Kit kit) {
        if(kit != null && !kits.containsKey(kit.getId())) {
            kits.put(kit.getId(), kit);
        }

    }

}
