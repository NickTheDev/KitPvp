package net.nikdev.kitpvp.user.stats;

import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.config.StoreException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Represents the store where {@link Statistics} are contained. The default backend is a MySQL store.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class DataStore {

    private static final List<String> defaults = Arrays.asList("pvp", "archer", "fisherman", "tank", "medic");
    private static final Executor executor = Executors.newCachedThreadPool();

    private final Database backend = new Database();

    /**
     * Creates a new stats store with the specified nitrite backend.
     */
    public DataStore() throws StoreException {
        try {
            backend.connect();
            backend.createTable("stats", "  (uuid VARCHAR(36) NOT NULL UNIQUE," + "   tokens INTEGER DEFAULT 0," + "   kits TEXT NOT NULL)");

        } catch (Exception e) {
            throw new StoreException("An error occurred opening the data store.", e);
        }

    }

    /**
     * Gets the statistic store registered to the specified unique id. If there is no store registered, one will be created.
     * This method is not ran isInside the executor service because it will only be called by an asynchronous event.
     *
     * @param id Id of the store.
     * @return Loaded statistic store or new one registered to the id.
     */
    public Statistics load(UUID id) {
        Statistics stats = null;
        Optional<ResultSet> result = backend.query("SELECT * FROM `stats` WHERE `uuid` = '" + id + "'");

        if(result.isPresent() && backend.next(result.get())) {
            try {
                stats = new Statistics(id, result.get().getInt("tokens"), new ArrayList<>(Arrays.asList(result.get().getString("kits").split(","))));

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            backend.update("INSERT INTO `stats` (`uuid`, `tokens`, `kits`) VALUES ('" + id + "', default, '" + String.join(",", defaults) + "')");
        }

        result.ifPresent(backend::close);
        return stats != null ? stats : new Statistics(id, 0, defaults);
    }

    /**
     * Updates the specified statistic store with new values. This will only be called when a {@link User} leaves the server
     * or the server shuts down. This should only be called with the async parameter false when the server is shutting down,
     * as it must be run synchronously then to make sure that all statistics save before closing the database.
     *
     * @param stats Statistic store to update.
     * @param async Whether or not the update should be executed asynchronously.
     */
    public void update(Statistics stats, boolean async) {
        if(async) {
            executor.execute(() -> backend.update("UPDATE `stats` SET " + stats.toString() + " WHERE `uuid` = '" + stats.getId() + "'"));

        } else {
            backend.update("UPDATE `stats` SET " + stats.toString() + " WHERE `uuid` = '" + stats.getId() + "'");
        }

    }

    /**
     * Closes the underlying backend.
     */
    public void close() {
        backend.close();
    }

}
