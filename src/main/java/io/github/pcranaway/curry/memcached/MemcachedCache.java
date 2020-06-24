package io.github.pcranaway.curry.memcached;

import io.github.pcranaway.curry.cache.Cache;

import java.util.UUID;

public class MemcachedCache implements Cache {

    @Override
    public void put(String name, UUID uuid) {

    }

    @Override
    public String getName(UUID uuid) {
        return null;
    }

    @Override
    public UUID getUUID(String name) {
        return null;
    }

}
