package cjc.service;

import cjc.model.Card;
import cjc.model.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckService {

    private Deck deck = new Deck();

    private List<String> CARD_NUMBERS = List.of(
            "J", "Q", "K", "A",
            "2", "3", "4", "5", "6", "7", "8", "9", "10"
    );
    private List<String> CARD_SUITS = List.of(
            "Clubs", "Spades", "Hearts", "Diamonds"
    );

    public DeckService(){
        populateDeck();
    }

    public void populateDeck(){
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            for (String number : CARD_NUMBERS){
                for (String suit : CARD_SUITS){
                    cards.add(new Card(number, suit));
                }
            }
        }
        Collections.shuffle(cards);
        deck.setCards(cards);
    }

    public void checkForReShuffle(){
        if (deck.getCards().size() <= 52) {
            this.populateDeck();
        }
    }

    public Card popCard(){
        Card c = deck.getCards().get(0);
        deck.getCards().remove(0);
        return c;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}