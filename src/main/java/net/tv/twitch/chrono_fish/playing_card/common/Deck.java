package net.tv.twitch.chrono_fish.playing_card.common;

import net.tv.twitch.chrono_fish.playing_card.common.Card;

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

    public Card draw() {
        // 山札が空の場合、nullを返す
        if (cards.size() < 1) return null;
        // 最初のカードを返して山札から削除
        return cards.remove(0);
    }

    public ArrayList<Card> draw(int count) {
        ArrayList<Card> cardList = new ArrayList<>();
        // 引く枚数を山札の残り枚数以内に制限
        int size = Math.min(cards.size(), count);
        for (int i = 0; i < size; i++) {
            cardList.add(cards.remove(0)); // 山札の先頭からカードを引く
        }
        return cardList;
    }
}
