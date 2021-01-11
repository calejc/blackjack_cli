package cjc.service;

import cjc.model.Card;
import cjc.model.Hand;

import java.util.List;

public class HandService {


    private Hand hand = new Hand();

    public void tally(){
        Integer tmp = 0;
        for (Card c : hand.getCards()){
            if (c.getVisible()){
                tmp += c.getValue();
            }
        }
        hand.setHandTotal(tmp);
        hand.setBust(this.checkForBust());
        if (this.containsAce() && this.hand.getBust()){
            this.changeAceValue();
        }
        hand.setBust(this.checkForBust());
    }

    public void addToHand(Card card){
        List<Card> tmp = hand.getCards();
        tmp.add(card);
        hand.setCards(tmp);
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "HandService{" +
                "hand=" + hand +
                '}';
    }

    public Boolean containsAce(){
        boolean containsAce = false;
        for (Card c : this.getHand().getCards()){
            if (c.getNumber().equalsIgnoreCase("A")) {
                containsAce = true;
                break;
            }
        }
        return containsAce;
    }

    public Boolean checkForBust(){
        return this.getHand().getHandTotal() > 21;
    }

    public Boolean checkForBlackjack(){
        return this.getHand().getHandTotal().equals(21);
    }

    public Boolean checkForDealerStand(){
        return this.getHand().getHandTotal() >= 17;
    }

    public void flipDealerCard(){
        for (Card c : this.getHand().getCards()){
            c.setVisible(true);
        }
    }

    public void clearHand(){
        if (this.getHand().getCards().size() > 0){
            this.getHand().getCards().clear();
        }
    }

    public void changeAceValue(){
        for (Card c : this.getHand().getCards()){
            if (c.getNumber().equalsIgnoreCase("A")){
                c.setValue(1);
                break;
            }
        }
    }

}
