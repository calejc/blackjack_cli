package cjc.service;

import cjc.model.Card;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MessageService {

    final String VERTICAL_LINE = "\u2502";
    final String HORIZONTAL_LINE = "\u2500";
    final String VERTICAL_DASHED = "\u254E";
    private Map<String, String> corners = Map.of(
            "rounded_upper_left", "\u256D",
            "rounded_upper_right", "\u256E",
            "rounded_bottom_left", "\u2570",
            "rounded_bottom_right", "\u256F",
            "square_upper_left", "\u250C",
            "square_upper_right", "\u2510",
            "square_bottom_left", "\u2514",
            "square_bottom_right", "\u2518"
    );
    private Map<String, String> suits = Map.of(
            "Clubs", "\u2663",
            "Spades", "\u2660",
            "Hearts", "\u2665",
            "Diamonds", "\u2666"
    );


    public MessageService(){}

    public String greetingMessage(){
        return "Hi! Welcome to my blackjack table!";
    }

    public String handAscii(List<Card> hand) {
        int spacer = 2;
        StringBuilder lineOne = new StringBuilder();
        StringBuilder lineTwo = new StringBuilder();
        StringBuilder blankLine = new StringBuilder();
        StringBuilder lineFour = new StringBuilder();
        StringBuilder lineSix = new StringBuilder();
        StringBuilder lineSeven = new StringBuilder();
        for (Card c : hand){
            lineOne.append(corners.get("rounded_upper_left") + HORIZONTAL_LINE.repeat(7) + corners.get("rounded_upper_right") + " ".repeat(spacer));
            lineSeven.append(corners.get("rounded_bottom_left") + HORIZONTAL_LINE.repeat(7) + corners.get("rounded_bottom_right") + " ".repeat(spacer));
            if (c.getVisible()){
                String number = c.getNumber();
                String suit = suits.get(c.getSuit());
                lineTwo.append(VERTICAL_LINE + number + " ".repeat(7-number.length()) + VERTICAL_LINE + " ".repeat(spacer));
                blankLine.append(VERTICAL_LINE + " ".repeat(7) + VERTICAL_LINE + " ".repeat(spacer));
                lineFour.append(VERTICAL_LINE + " ".repeat(3) + suit + " ".repeat(3) + VERTICAL_LINE + " ".repeat(spacer));
                lineSix.append(VERTICAL_LINE + " ".repeat(7-number.length()) + number + VERTICAL_LINE + " ".repeat(spacer));
            } else {
                String fill = VERTICAL_LINE + VERTICAL_DASHED.repeat(7) + VERTICAL_LINE + " ".repeat(spacer);
                lineTwo.append(fill);
                blankLine.append(fill);
                lineFour.append(fill);
                lineSix.append(fill);
            }
        }
        return lineOne + "\n" + lineTwo + "\n" + blankLine + "\n" + lineFour + "\n" + blankLine + "\n" + lineSix + "\n" + lineSeven;
    }

    public String messageBox(String message){
        return corners.get("square_upper_left") + HORIZONTAL_LINE.repeat(message.length()) + corners.get("square_upper_right") + "\n" + VERTICAL_LINE + message + VERTICAL_LINE + "\n" + corners.get("square_bottom_left") + HORIZONTAL_LINE.repeat(message.length()) + corners.get("square_bottom_right");
    }

    public String handHeader(String title, HandService hand){
        return messageBox(String.format("  %s : %d  ", title, hand.getHand().getHandTotal()));
    }


    public String bustMessage(String player){
        return "\n" + messageBox(String.format("  ::%s BUSTS::  ", player));
    }

    public String blackjackMessage(String player){
        return "\n" + messageBox(String.format("  ::%s BLACKJACK::  ", player));
    }

    public String dealerVictoryMessage(){
        List<String> dealerVictoryMessages = List.of(
                "Dealer wins... try harder next time.",
                "Again the dealer wins. How many times can this happen?!",
                "The dealer takes your money again... ",
                "You lose"
            );
        return "\n" + returnRandomMessage(dealerVictoryMessages);
    }

    public String userVictoryMessage(){
        List<String> userVictoryMessages = List.of(
                "You win!!",
                "Congrats on finally beating the dealer..",
                "Nice hand, try to do it again!"
        );
        return "\n" + returnRandomMessage(userVictoryMessages);
    }

    public String returnRandomMessage(List<String> messageList){
        Random rand = new Random();
        return messageList.get(rand.nextInt(messageList.size()));
    }


}
