package io.github.pcranaway.curry.cache.type.impl;

import io.github.pcranaway.curry.cache.type.CacheType;

public class MemcachedCacheType implements CacheType {

    @Override
    public String getName() {
        return "memcached";
    }

}
