package io.github.pcranaway.curry;

import io.github.pcranaway.curry.cache.CacheModule;
import io.github.pcranaway.curry.cache.type.impl.MemcachedCacheType;
import io.github.pcranaway.curry.cache.type.impl.MemoryCacheType;
import io.github.pcranaway.curry.cache.type.impl.RedisCacheType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CurryLoader extends JavaPlugin {

    private CacheModule cacheModule;

    @Override
    public void onEnable() {
        // load configuration
        this.saveDefaultConfig();

        // load cache module
        this.cacheModule = new CacheModule(Arrays.asList(
                new MemoryCacheType(),
                new MemcachedCacheType(),
                new RedisCacheType()
        ));
    }

    /**
     * Gets the {@link CacheModule}
     *
     * @return the {@link CacheModule}
     */
    public CacheModule getCacheModule() {
        return cacheModule;
    }

    /**
     * Gets the {@link CurryLoader}
     *
     * @return {@link CurryLoader}
     */
    public static CurryLoader getCurryLoader() {
        return JavaPlugin.getPlugin(CurryLoader.class);
    }

}
