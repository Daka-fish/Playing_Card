package net.tv.twitch.chrono_fish.playing_card.indian_poker;

import net.tv.twitch.chrono_fish.playing_card.common.Card;
import net.tv.twitch.chrono_fish.playing_card.common.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class IndianPoker {

    private final ArrayList<IpPlayer> participants;
    private Deck deck;
    private boolean isRunning;

    private final HashMap<IpPlayer, Card> cardHashMap;

    public IndianPoker(){
        this.participants = new ArrayList<>();
        this.deck = new Deck();
        this.isRunning = false;
        this.cardHashMap = new HashMap<>();
        deck.shuffle();
    }

    public ArrayList<IpPlayer> getParticipants() {return participants;}

    public Deck getDeck() {return deck;}
    public void setDeck(Deck deck) {this.deck = deck;}

    public boolean isRunning() {return isRunning;}
    public void setRunning(boolean running) {isRunning = running;}

    public HashMap<IpPlayer, Card> getCardHashMap() {return cardHashMap;}

    public void sendMessage(String message){
        participants.forEach(ipPlayer -> {
            if (ipPlayer.getPlayer() != null) ipPlayer.getPlayer().sendMessage(message);
        });
    }

    public void addPlayer(IpPlayer ipPlayer){
        participants.add(ipPlayer);
        sendMessage("§a"+ipPlayer.getName()+"§eがゲームに参加しました");
    }

    public void addCpu(int number){
        for(int i=0; i<number; i++){
            participants.add(new DummyPlayer(this,"nano"+participants.size()));
            sendMessage("§eCPUがゲームに参加しました");
        }
    }

    public void start(){
        if(isRunning){
            sendMessage("§c既にゲームが進行中です");
            return;
        }

        if(participants.size()<2){
            sendMessage("§c参加者が不足しています。あと("+(2-participants.size())+"人)");
            return;
        }

        Collections.shuffle(participants);
        isRunning = true;
        for(IpPlayer player : participants){
            cardHashMap.put(player,deck.draw());
            //自分以外のカード情報の表示
        }
    }

    public void finish(){
        if(!isRunning){
            return;
        }

        IpPlayer winner = participants.get(0);
        IpPlayer loser = participants.get(0);
        for(IpPlayer ipPlayer : participants){
            if(cardHashMap.containsKey(ipPlayer)){
                if(cardHashMap.get(ipPlayer).getNumber() > cardHashMap.get(winner).getNumber()){
                    winner = ipPlayer;
                }
                if(cardHashMap.get(ipPlayer).getNumber() < cardHashMap.get(loser).getNumber()){
                    loser = ipPlayer;
                }
            }
            String role = (ipPlayer == winner) ? "[勝者]" : (ipPlayer == loser) ? "[敗者]" : "";
            sendMessage(ipPlayer.getName() + " : " + cardHashMap.get(ipPlayer).getNumber() + " " + role);
        }

        isRunning = false;
    }
}
