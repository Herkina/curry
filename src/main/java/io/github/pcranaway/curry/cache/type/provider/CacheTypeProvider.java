package io.github.pcranaway.curry.cache.type.provider;

import io.github.pcranaway.curry.cache.type.CacheType;

/**
 * Provides methods for interacting with the {@link CacheType}
 */
public interface CacheTypeProvider {

    /**
     * Finds a {@link CacheType}
     *
     * @param name the name of the {@link CacheType}
     * @return the {@link CacheType}
     */
    CacheType find(String name);

    /**
     * Registers a {@link CacheType}
     *
     * @param cacheType the {@link CacheType}
     */
    void register(CacheType cacheType);

}
