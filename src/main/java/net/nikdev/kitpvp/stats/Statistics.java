package net.nikdev.kitpvp.stats;

import com.google.common.collect.ImmutableList;
import net.nikdev.kitpvp.user.User;
import org.dizitart.no2.Document;
import org.dizitart.no2.mapper.Mappable;
import org.dizitart.no2.mapper.NitriteMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Represents the statistics of a {@link User}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Statistics implements Mappable {

    private UUID id;
    private int tokens;

    private final List<String> kits = new ArrayList<>();

    /**
     * Implemented to be compatible as a {@link Mappable}.
     */
    public Statistics() {}

    /**
     * Creates a new statistic store with the specified unique id.
     *
     * @param id Id of this statistic store.
     */
    Statistics(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique id statistic of this store.
     *
     * @return Unique id statistic.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the token statistic from this store.
     *
     * @return Token statistic.
     */
    public int getTokens() {
        return tokens;
    }

    /**
     * Gets the kits statistic from this store.
     *
     * @return Kits statistic.
     */
    public List<String> getKits() {
        return ImmutableList.copyOf(kits);
    }

    /**
     * Adds the specified amount of tokens to the token statistic.
     *
     * @param amount Amount of tokens to add.
     */
    public void addTokens(int amount) {
        tokens += amount;
    }

    /**
     * Removes the specified amount of tokens from the token statistic.
     *
     * @param amount Amount of tokens to remove.
     */
    public void removeTokens(int amount) {
        tokens -= amount;
    }

    /**
     * Adds the specified kit to the kits statistic if it doesn't already
     * contain the kit.
     *
     * @param kit Kit to add.
     */
    public void addKit(String kit) {
        if(!getKits().contains(kit)) {
            kits.add(kit);
        }

    }

    @Override
    public Document write(NitriteMapper mapper) {
        Document document = new Document();

        document.put("id", getId().toString());
        document.put("tokens", getTokens());
        document.put("kits", String.join(":", getKits()));

        return document;
    }

    @Override
    public void read(NitriteMapper mapper, Document document) {
        id = UUID.fromString(document.get("id", String.class));
        tokens = document.get("tokens", Integer.class);
        kits.addAll(Arrays.asList(document.get("kits", String.class).split(":")));
    }

}