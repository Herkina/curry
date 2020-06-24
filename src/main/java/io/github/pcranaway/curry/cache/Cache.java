package io.github.pcranaway.curry.cache;

import java.util.UUID;

public interface Cache<T> {

    /**
     * Puts a name and a UUID to the cache
     *
     * @param name the name
     * @param uuid the UUID
     */
    void put(String name, UUID uuid);

    /**
     * Gets the name of the player
     *
     * @param uuid the UUID of the player
     * @return the name of the player
     */
    String getName(UUID uuid);

    /**
     * Gets the UUID of a player
     *
     * @param name the name of the player
     * @return the UUID of the player
     */
    UUID getUUID(String name);

}
