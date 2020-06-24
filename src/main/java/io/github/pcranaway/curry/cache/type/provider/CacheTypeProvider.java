package io.github.pcranaway.curry.cache.type.provider;

import io.github.pcranaway.curry.cache.type.CacheType;

public interface CacheTypeProvider {

    CacheType find(String name);

    void register(CacheType cacheType);

}
