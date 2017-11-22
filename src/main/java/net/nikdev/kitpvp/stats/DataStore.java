package net.nikdev.kitpvp.stats;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.lang.ConfigKeys;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.StoreException;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.exceptions.NitriteIOException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static net.nikdev.kitpvp.lang.ConfigKeys.USER_KEY;
import static net.nikdev.kitpvp.lang.ConfigKeys.AUTH_KEY;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 * Represents the store where {@link Statistics} are contained.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class DataStore {

    private final Nitrite backend;

    /**
     * Creates a new stats store with the specified nitrite backend.
     */
    public DataStore() throws StoreException {
        try {
            Path directory = new File(KitPvp.get().getDataFolder(), "stats").toPath();

            if(Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            backend = Nitrite.builder().compressed().filePath(KitPvp.get().getDataFolder() + "/stats/users.nitrite")
                    .openOrCreate(ConfigKeys.get(USER_KEY), ConfigKeys.get(AUTH_KEY));

        } catch(NitriteIOException | IOException e) {
            throw new StoreException("Unable to open Nitrite data store.", e);
        }

    }

    /**
     * Gets the statistic store registered to the specified unique id. If there is no store registered, one will be created.
     *
     * @param id Id of the store.
     * @return Loaded statistic store or new one registered to the id.
     */
    public Statistics load(UUID id) {
        Statistics stats = backend.getRepository(Statistics.class).find(eq("id", id.toString())).firstOrDefault();

        if(stats == null) {
            stats = new Statistics(id);
            backend.getRepository(Statistics.class).insert(stats);
        }

        return stats;
    }

    /**
     * Updates the specified statistic store with new values. This will only be called when a {@link User} leaves the server
     * or the server shuts down.
     *
     * @param stats Statistic store to update.
     */
    public void update(Statistics stats) {
        backend.getRepository(Statistics.class).update(stats);
    }

    /**
     * Closes this data store.
     */
    public void close() {
        if(!backend.isClosed()) {
            backend.commit();
            backend.close();
        }

    }

}
