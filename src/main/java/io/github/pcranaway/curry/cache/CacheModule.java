package io.github.pcranaway.curry.cache;

import io.github.pcranaway.curry.cache.type.CacheType;
import io.github.pcranaway.curry.cache.type.provider.CacheTypeProvider;
import io.github.pcranaway.curry.cache.type.provider.SimpleCacheTypeProvider;

import java.util.List;

/**
 * Initializes and handles the cache
 */
public class CacheModule {

    private final CacheTypeProvider cacheTypeProvider;

    /**
     * Creates the {@link CacheModule}
     *
     * @param cacheTypeList the list of {@link CacheType} to be registered
     */
    public CacheModule(List<CacheType> cacheTypeList) {
        this.cacheTypeProvider = new SimpleCacheTypeProvider();

        cacheTypeList.forEach(this.cacheTypeProvider::register);
    }

}
