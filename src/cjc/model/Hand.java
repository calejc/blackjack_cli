package cjc.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards = new ArrayList<>();
    private Integer handTotal;
    private Boolean bust;

    public Hand(){}

    public void tally(){
        Integer tmp = 0;
        for (Card c : this.getCards()){
            tmp += c.getValue();
        }
        setHandTotal(tmp);
    }

    public void addToHand(Card card){
        List<Card> tmp = this.getCards();
        tmp.add(card);
        this.setCards(tmp);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Integer getHandTotal() {
        return handTotal;
    }

    public void setHandTotal(Integer handTotal) {
        this.handTotal = handTotal;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", handTotal=" + handTotal +
                '}';
    }

    public Boolean getBust() {
        return bust;
    }

    public void setBust(Boolean bust) {
        this.bust = bust;
    }
}

