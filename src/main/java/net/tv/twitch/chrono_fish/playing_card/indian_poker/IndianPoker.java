package net.tv.twitch.chrono_fish.playing_card.indian_poker;

import net.tv.twitch.chrono_fish.playing_card.common.Card;
import net.tv.twitch.chrono_fish.playing_card.common.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class IndianPoker {

    private final ArrayList<Ip_player> participants;
    private Deck deck;
    private boolean isRunning;

    private final HashMap<Ip_player, Card> playerHash;

    public IndianPoker(){
        this.participants = new ArrayList<>();
        this.deck = new Deck();
        this.isRunning = false;
        this.playerHash = new HashMap<>();
        deck.shuffle();
    }

    public ArrayList<Ip_player> getParticipants() {return participants;}

    public Deck getDeck() {return deck;}
    public void setDeck(Deck deck) {this.deck = deck;}

    public boolean isRunning() {return isRunning;}
    public void setRunning(boolean running) {isRunning = running;}

    public HashMap<Ip_player, Card> getPlayerHash() {return playerHash;}

    public void sendMessage(String message){participants.forEach(ipPlayer -> ipPlayer.getPlayer().sendMessage(message));}

    public void start(){
        if(isRunning){
            return;
        }

        if(participants.size()<2){
            return;
        }

        Collections.shuffle(participants);
        isRunning = true;
        for(Ip_player player : participants){
            playerHash.put(player,deck.draw());
        }
    }

    public void finish(){
        if(!isRunning){
            return;
        }

        Ip_player winner = participants.get(0);
        Ip_player loser = participants.get(0);
        for(Ip_player ipPlayer : participants){
            if(playerHash.containsKey(ipPlayer)){
                if(playerHash.get(ipPlayer).getNumber() > playerHash.get(winner).getNumber()){
                    winner = ipPlayer;
                }
                if(playerHash.get(ipPlayer).getNumber() < playerHash.get(loser).getNumber()){
                    loser = ipPlayer;
                }
            }
            String role = (ipPlayer == winner) ? "[勝者]" : (ipPlayer == loser) ? "[敗者]" : "";
            sendMessage(ipPlayer.getPlayer().getName() + " : " + playerHash.get(ipPlayer).getNumber() + " " + role);
        }

        isRunning = false;
    }
}
