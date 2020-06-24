package io.github.pcranaway.curry.memory;

import io.github.pcranaway.curry.cache.Cache;

import java.util.HashMap;
import java.util.UUID;

public class MemoryCache implements Cache {

    private final HashMap<String, UUID> cache;

    public MemoryCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public void put(String name, UUID uuid) {
        this.cache.put(name.toLowerCase(), uuid);
    }

    @Override
    public String getName(UUID uuid) {
        return this.cache.entrySet().stream()
                .filter(entry -> entry.getValue().equals(uuid))
                .findFirst()
                .orElse(null)
                .getKey();
    }

    @Override
    public UUID getUUID(String name) {
        return this.cache.get(name);
    }

}
