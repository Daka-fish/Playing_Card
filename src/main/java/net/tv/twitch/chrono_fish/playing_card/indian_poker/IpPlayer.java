package net.tv.twitch.chrono_fish.playing_card.indian_poker;

import net.tv.twitch.chrono_fish.playing_card.common.Card;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IpPlayer {

    private final IndianPoker indianPoker;
    private final Player player;
    private final String name;
    private Card card;
    private boolean hasDraw;

    public IpPlayer(IndianPoker indianPoker, String name){
        this.indianPoker = indianPoker;
        this.player = Bukkit.getPlayerExact(name);
        this.name = name;
        this.hasDraw = false;
    }

    public IpPlayer(IndianPoker indianPoker, Player player){
        this.indianPoker = indianPoker;
        this.player = player;
        this.name = player.getName();
        this.hasDraw = false;
    }

    public IndianPoker getIndianPoker() {return indianPoker;}
    public Player getPlayer() {return player;}
    public String getName() {return name;}
    public Card getCard() {return card;}
    public void setCard(Card card) {this.card = card;}

    public Card draw(){
        if(hasDraw){
            player.sendMessage("§c既にドローしているため、ドローできません");
            return null;
        }
        hasDraw = true;
        Card topCard = indianPoker.getDeck().draw();
        player.sendMessage("§e"+topCard.toString()+"§fをドローしました");
        return topCard;
    }
}
