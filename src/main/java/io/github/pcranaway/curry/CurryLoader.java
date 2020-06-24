package io.github.pcranaway.curry;

import io.github.pcranaway.curry.cache.Cache;
import io.github.pcranaway.curry.memcached.MemcachedCache;
import io.github.pcranaway.curry.memory.MemoryCache;
import io.github.pcranaway.curry.redis.RedisCache;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class CurryLoader extends JavaPlugin {

    private Cache cache;

    @Override
    public void onEnable() {
        // load configuration
        this.saveDefaultConfig();

        // detect cache type and create the cache
        switch(this.getConfig().getString("cache.type")) {

            case "memory":
                this.cache = new MemoryCache();
                break;

            case "memcached":
                this.cache = new MemcachedCache();
                break;

            case "redis":
                this.cache = new RedisCache();
                break;

            default:
                System.out.println("invalid cache type");
                break;

        }
    }

    /**
     * Gets the UUID of a player by their name
     *
     * @param name the name of the player
     */
    public UUID getUUID(String name) {
        // try to find UUID in cache
        UUID found = this.cache.getUUID(name);

        // if it was found, return it
        if(found != null) return found;

        // if it was not found, fetch it and add it to the cache
        found = this.fetchUUID(name);
        this.cache.put(name, found);

        return found;
    }

    public String getName(UUID uuid) {
        // try to find the name in the cache
        String found = this.cache.getName(uuid);

        // if it was found, return it
        if(found != null || !found.isEmpty()) return found;

        // if it was not found, fetch it and add it to the cache
        found = this.fetchName(uuid);
        this.cache.put(found, uuid);

        return found;
    }

    /**
     * Fetches the name of a player from mojang's api
     *
     * @param uuid the UUID of the player
     * @return the name of the player
     */
    private String fetchName(UUID uuid) {
        return null;
    }

    /**
     * Fetches the UUID of a player from mojang's api
     *
     * @param name the name of the player
     */
    public UUID fetchUUID(String name) {
        return null;
    }

    /**
     * Gets the {@link CurryLoader}
     *
     * @return {@link CurryLoader}
     */
    public static CurryLoader getCurryLoader() {
        return JavaPlugin.getPlugin(CurryLoader.class);
    }

    /**
     * Gets the cache
     *
     * @return the cache
     */
    public Cache getCache() {
        return this.cache;
    }

}
