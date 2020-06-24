package io.github.pcranaway.curry.cache.type.provider;

import io.github.pcranaway.curry.cache.type.CacheType;

import java.util.HashSet;

public class SimpleCacheTypeProvider implements CacheTypeProvider {

    private final HashSet<CacheType> cacheTypes;

    public SimpleCacheTypeProvider() {
        this.cacheTypes = new HashSet<>();
    }

    @Override
    public CacheType find(String name) {
        return this.cacheTypes.stream()
                .filter(cacheType -> cacheType.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void register(CacheType cacheType) {
        this.cacheTypes.add(cacheType);
    }

}
