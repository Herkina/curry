package io.github.pcranaway.curry.cache.type.impl;

import io.github.pcranaway.curry.cache.type.CacheType;

public class MemoryCacheType implements CacheType {

    @Override
    public String getName() {
        return "memory";
    }

}
