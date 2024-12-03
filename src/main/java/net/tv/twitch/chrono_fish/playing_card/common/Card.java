package net.tv.twitch.chrono_fish.playing_card.common;

public class Card {

    private int number;
    private String mark;

    public Card(int number, String mark){
        this.number = number;
        this.mark = mark;
    }

    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}

    public String getMark() {return mark;}
    public void setMark(String mark) {this.mark = mark;}

    @Override
    public String toString() {
        return mark + " " + number;
    }
}
