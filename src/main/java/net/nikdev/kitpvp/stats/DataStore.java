package net.nikdev.kitpvp.stats;

import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.StoreException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Represents the store where {@link Statistics} are contained.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class DataStore {

    private final DataBackend backend = new DataBackend();

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
     * Should only be called asynchronously.
     *
     * @param id Id of the store.
     * @return Loaded statistic store or new one registered to the id.
     */
    public Statistics load(UUID id) {
        Statistics stats = null;
        Optional<ResultSet> result = backend.query("SELECT * FROM `stats` WHERE `uuid` = '" + id + "'");

        if(result.isPresent() && backend.next(result.get())) {
            try {
                stats = new Statistics(id, result.get().getInt("tokens"), Arrays.asList(result.get().getString("kits").split(",")));

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            backend.update("INSERT INTO `stats` (`uuid`, `tokens`, `kits`) VALUES ('" + id + "', default, 'pvp')");
        }

        result.ifPresent(backend::close);
        return stats != null ? stats : new Statistics(id, 0, Collections.singletonList("pvp"));
    }

    /**
     * Updates the specified statistic store with new values. This will only be called when a {@link User} leaves the server
     * or the server shuts down
     *
     * @param stats Statistic store to update.
     */
    public void update(Statistics stats) {
        backend.update("UPDATE `stats` SET " + stats.toString() + " WHERE `uuid` = '" + stats.getId() + "'");
    }

    /**
     * Closes this data store.
     */
    public void close() {
        backend.close();
    }

}
