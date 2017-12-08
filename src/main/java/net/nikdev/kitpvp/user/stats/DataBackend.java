package net.nikdev.kitpvp.user.stats;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.Config;

import java.sql.*;
import java.util.Optional;

/**
 * Wrapper for a MySQL connection.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class DataBackend {

    private Connection connection;

    /**
     * Utility class, not to be instantiated by other classes outside of this package.
     */
    DataBackend() {}

    /**
     * Gets if this database's connection is alive.
     *
     * @return If this database's connection is alive.
     */
    public boolean isConnected() {
        return connection != null;
    }

    /**
     * Utility for checking if a result set has a next value without having to create a try/catch to clean code up.
     *
     * @param set Set to check.
     * @return If the result set has a next value.
     */
    public boolean next(ResultSet set) {
        try {
            return set.next();

        } catch (SQLException e) {
            KitPvp.get().getLogger().severe("An error occurred checking a database query.");

            e.printStackTrace();
        }

        return false;
    }

    /**
     * Utility for closing a result set without having to create a try/catch to clean code up.
     *
     * @param set Set to close.
     */
    public void close(ResultSet set) {
        try {
            set.close();

        } catch (SQLException e) {
            KitPvp.get().getLogger().severe("An error occurred closing a database query.");

            e.printStackTrace();
        }

    }

    /**
     * Attempts to connect this database using KitPvp's config connection options.
     *
     * @throws Exception Thrown if an error occurs opening this database.
     */
    public void connect() throws Exception {
        if(!isConnected()) {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://" + Config.get(Config.DATABASE_ADDRESS) + ":" + Config.getInt(Config.DATABASE_PORT) + "/" +
                    Config.get(Config.DATABASE_NAME) + "?autoReconnect=true", Config.get(Config.DATABASE_USER), Config.get(Config.DATABASE_PASSWORD));
        }

    }

    /**
     * Attempts to close this database's connection if it is alive.
     */
    public void close() {
        if(isConnected()) {
            try {
                connection.close();
                connection = null;

            } catch(SQLException e) {
                KitPvp.get().getLogger().severe("An error occurred closing database.");

                e.printStackTrace();
            }

        }

    }

    /**
     * Attempts to get the query result set of the specified query string if this database's connection is alive.
     *
     * @param query String to query.
     * @return Result set of the query.
     */
    public Optional<ResultSet> query(String query) {
        if(isConnected()) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);

                return Optional.of(statement.executeQuery());

            } catch(SQLException e) {
                KitPvp.get().getLogger().severe("An error occurred querying sql in database.");

                e.printStackTrace();
            }

        }

        return Optional.empty();
    }

    /**
     * Attempts to execute the specified query string if the connection is alive.
     *
     * @param query Query to be executed.
     */
    public void update(String query) {
        if(isConnected()) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();
                statement.close();

            } catch(SQLException e) {
                KitPvp.get().getLogger().severe("An error occurred updating sql in database.");

                e.printStackTrace();
            }

        }

    }

    /**
     * Attempts to create the specified table with the specified columns if it does not exist.
     *
     * @param table Table to be created.
     * @param columns Columns of the table.
     */
    public void createTable(String table, String columns) {
        update("CREATE TABLE IF NOT EXISTS " + table + columns);
    }

}
