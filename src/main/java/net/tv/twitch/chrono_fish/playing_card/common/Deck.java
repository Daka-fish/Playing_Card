package net.tv.twitch.chrono_fish.playing_card;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<>();
        String[] marks = {"♥", "♦", "♣", "♠"};
        for(String mark : marks){
            for(int i=0; i<13; i++){
                cards.add(new Card(i+1,mark));
            }
        }
    }

    public ArrayList<Card> getCards() {return cards;}
    public void setCards(ArrayList<Card> cards) {this.cards = cards;}

    public int getCardCount() {return cards.size();}

    public void shuffle(){Collections.shuffle(cards);}

    public Card draw(){
        if(cards.size()<1) return null;
        return cards.remove(0);
    }

    public ArrayList<Card> draw(int count){
        ArrayList<Card> cardList = new ArrayList<>();
        int size = Math.min(cards.size(), count);
        for(int i=0; i<size; i++){
            cardList.add(cards.remove(0));
        }
        return cardList;
    }
}
