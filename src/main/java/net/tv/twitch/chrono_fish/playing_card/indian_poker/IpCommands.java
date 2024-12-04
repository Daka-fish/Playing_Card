package net.tv.twitch.chrono_fish.playing_card.indian_poker;

import net.tv.twitch.chrono_fish.playing_card.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IpCommands {
    private final Main main;
    private final Player sender;
    private final String[] args;

    public IpCommands(Main main, Player snd, String[] args){
        this.main = main;
        this.sender = snd;
        this.args = args;
    }

    public void run(){
        switch (args[0]){
            case "make":
                IndianPoker indianPoker = new IndianPoker();
                indianPoker.addPlayer(new IpPlayer(indianPoker,sender));
                main.addGame(sender,indianPoker);
                sender.sendMessage("§eゲームを作成しました");
                break;

            case "start":
                if(main.getGames().containsKey(sender)){
                    main.getGames().get(sender).start();
                }else{
                    sender.sendMessage("§cあなたが作成したゲームがありません");
                }
                break;

            case "add":
                //ip add {someone}
                if(main.getGames().containsKey(sender)){
                    IndianPoker existIp = main.getGames().get(sender);
                    if(args.length == 2){
                        if(args[1].equalsIgnoreCase("cpu")){
                            existIp.addCpu(1);
                        }else{
                            Player player = Bukkit.getPlayerExact(args[1]);
                            if(player != null){
                                IpPlayer ipPlayer = new IpPlayer(existIp,player);
                                existIp.addPlayer(ipPlayer);
                            }else{
                                sender.sendMessage("§cプレイヤーが見つかりません");
                            }
                        }
                    }else{
                        sender.sendMessage("§c"+"you send an unknown command\n/ip add {someone}");
                    }
                }else{
                    sender.sendMessage("§cあなたが作成したゲームがありません");
                }
                break;

            case "remove":
                if(main.getGames().containsKey(sender)){
                    main.removeGame(sender);
                }else{
                    sender.sendMessage("§cあなたが作成したゲームがありません");
                }
                break;

            default:
                sender.sendMessage("§c"+"you send an unknown command\n/ip {make, start, add}");
                break;
        }
    }
}
