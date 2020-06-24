package io.github.pcranaway.curry.cache;

import io.github.pcranaway.curry.cache.type.CacheType;
import io.github.pcranaway.curry.cache.type.CacheTypeProvider;
import io.github.pcranaway.curry.cache.type.SimpleCacheTypeProvider;

import java.util.List;

public class CacheModule {

    private final CacheTypeProvider cacheTypeProvider;

    public CacheModule(List<CacheType> cacheTypeList) {
        this.cacheTypeProvider = new SimpleCacheTypeProvider();

        cacheTypeList.forEach(this.cacheTypeProvider::register);
    }

}
