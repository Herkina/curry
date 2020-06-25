package io.github.pcranaway.curry;

import com.google.gson.Gson;
import io.github.pcranaway.curry.cache.Cache;
import io.github.pcranaway.curry.memcached.MemcachedCache;
import io.github.pcranaway.curry.memory.MemoryCache;
import io.github.pcranaway.curry.redis.RedisCache;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class CurryLoader extends JavaPlugin {

    private Cache cache;
    private HttpClient httpClient;
    private Gson gson;

    @Override
    public void onEnable() {

        // load configuration
        this.saveDefaultConfig();

        // detect cache type and create the cache
        switch (this.getConfig().getString("cache.type")) {

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

        // create a http client
        this.httpClient = new DefaultHttpClient();

        // create a gson instance
        this.gson = new Gson();
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
        if (found != null) return found;

        // if it was not found, fetch it and add it to the cache
        found = this.fetchUUID(name);
        this.cache.put(name, found);

        return found;
    }

    public String getName(UUID uuid) {
        // try to find the name in the cache
        String found = this.cache.getName(uuid);

        // if it was found, return it
        if (found != null || !found.isEmpty()) return found;

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

        // remove the hyphens from the UUID so mojang's API can understand it
        String formattedUUID = uuid.toString().replace("-", "");

        try {

            // send the request
            HttpGet request = new HttpGet("https://sessionserver.mojang.com/session/minecraft/profile/" + formattedUUID);
            HttpResponse response = this.httpClient.execute(request);

            // read the response
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String textResponse = bufferedReader.lines().collect(Collectors.joining(" "));

            // parse the response
            Map map = gson.fromJson(textResponse, Map.class);
            return map.get("name").toString();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;

    }

    /**
     * Fetches the UUID of a player from mojang's api
     *
     * @param name the name of the player
     */
    public UUID fetchUUID(String name) {

        try {

            // create the request
            HttpPost request = new HttpPost("https://api.mojang.com/profiles/minecraft");

            // create the entity
            StringEntity entity = new StringEntity("[\"" + name + "\"]");
            request.setEntity(entity);
            request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            // send the request
            HttpResponse response = this.httpClient.execute(request);

            // read the response
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String textResponse = bufferedReader.lines().collect(Collectors.joining(" "));

            // parse the response
            List list = gson.fromJson(textResponse, List.class);
            Map profile = (Map) list.get(0);
            String stringUUID = (String) profile.get("id");
            UUID uuid = UUID.fromString(this.formatUUID(stringUUID));

            return uuid;

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;

    }

    /**
     * Stolen from \/
     * https://github.com/desht/dhutils/blob/master/Lib/src/main/java/me/desht/dhutils/UUIDFetcher.java
     *
     * Formats a UUID given by mojang and adds hyphens so it can be read by UUID#fromString
     *
     * @param id
     * @return
     */
    private String formatUUID(String id) {
        return id.substring(0, 8) + "-" + id.substring(8, 12) + "-" + id.substring(12, 16) + "-" + id.substring(16, 20) + "-" +id.substring(20, 32);
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

