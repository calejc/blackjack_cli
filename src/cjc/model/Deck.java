package cjc.model;

import java.util.List;

public class Deck {

    public List<Card> cards;

    public Deck(){}

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}

