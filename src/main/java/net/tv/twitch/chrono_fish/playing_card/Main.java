package net.tv.twitch.chrono_fish.playing_card;

import net.tv.twitch.chrono_fish.playing_card.common.Commands;
import net.tv.twitch.chrono_fish.playing_card.indian_poker.IndianPoker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    private HashMap<Player, IndianPoker> games;

    @Override
    public void onEnable() {
        this.games = new HashMap<>();
        getCommand("ip").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public HashMap<Player, IndianPoker> getGames() {return games;}

    public void addGame(Player player, IndianPoker indianPoker){
        this.games.put(player, indianPoker);
        player.sendMessage("§eゲームを作成しました");
    }

    public void removeGame(Player player){
        this.games.remove(player);
        player.sendMessage("§eゲームを削除しました");
    }

    public void consoleLog(String message){getLogger().info(message);}
}
