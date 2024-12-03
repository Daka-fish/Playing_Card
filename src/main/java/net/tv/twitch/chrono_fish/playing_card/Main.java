package net.tv.twitch.chrono_fish.playing_card;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void consoleLog(String message){getLogger().info(message);}
}
