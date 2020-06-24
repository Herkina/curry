package io.github.pcranaway.curry.cache;

import io.github.pcranaway.curry.cache.type.CacheType;
import io.github.pcranaway.curry.cache.type.provider.CacheTypeProvider;
import io.github.pcranaway.curry.cache.type.provider.SimpleCacheTypeProvider;

import java.util.List;

public class CacheModule {

    private final CacheTypeProvider cacheTypeProvider;

    public CacheModule(List<CacheType> cacheTypeList) {
        this.cacheTypeProvider = new SimpleCacheTypeProvider();

        cacheTypeList.forEach(this.cacheTypeProvider::register);
    }

}
