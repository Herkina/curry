package io.github.pcranaway.curry;

import org.bukkit.plugin.java.JavaPlugin;

public class CurryLoader extends JavaPlugin {

    @Override
    public void onEnable() {
        // load configuration
        this.saveDefaultConfig();
    }

}
