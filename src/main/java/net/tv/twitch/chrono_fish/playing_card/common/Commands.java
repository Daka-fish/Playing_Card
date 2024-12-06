package net.tv.twitch.chrono_fish.playing_card.common;

import net.tv.twitch.chrono_fish.playing_card.Main;
import net.tv.twitch.chrono_fish.playing_card.indian_poker.IpCommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {

    private final Main main;

    public Commands(Main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player){
            Player snd = (Player) sender;
            switch (s){
                case "ip":
                    new IpCommands(main,snd,args).run();
                    break;

                default:
                    snd.sendMessage("§c不明なコマンド");
            }
        }
        return false;
    }
}
