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

    public void sendMessage(String message){participants.forEach(ipPlayer -> ipPlayer.sendMessage(message));}

    public void addPlayer(IpPlayer ipPlayer){
        participants.add(ipPlayer);
        sendMessage("§a"+ipPlayer.getName()+"§eがインディアンポーカーに参加しました");
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
        for (IpPlayer player : participants) {
            cardHashMap.put(player, deck.draw());
        }
        // 参加者ごとに自分以外のカードを表示

    }

    public void finish() {
        if (!isRunning) {
            sendMessage("§cゲームは進行していません。");
            return;
        }

        // ゲーム終了メッセージ
        sendMessage("§eインディアンポーカーが終了しました。");

        // 勝者を決定し表示
        IpPlayer winner = participants.get(0);
        for (IpPlayer player : participants) {
            if (cardHashMap.containsKey(player)) {
                Card currentCard = cardHashMap.get(player);
                if (cardHashMap.get(winner).getNumber() < currentCard.getNumber()) {
                    winner = player;
                }
            }
        }
        sendMessage("§a勝者: §b" + winner.getName());

        // ゲーム状態リセット
        isRunning = false;
        cardHashMap.clear();

        // デッキの再初期化
        deck = new Deck();
        deck.shuffle();
    }

}
