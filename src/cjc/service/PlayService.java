package cjc.service;

import java.util.Scanner;

public class PlayService {

    MessageService messageService = new MessageService();
    DeckService deckService = new DeckService();
    HandService userHand = new HandService();
    HandService dealerHand = new HandService();

    public void play() throws InterruptedException {
        stdout(messageService.greetingMessage());
        do {
            userHand.clearHand();
            dealerHand.clearHand();
            initialDeal();
            while(dealAnotherToUser()){
                    deal(userHand);
                }
            while(!handOver()){
                dealerHand.flipDealerCard();
                deal(dealerHand);
                Thread.sleep(3000);
            }
            if (userHand.checkForBust()){
                stdout(messageService.bustMessage("USER"));
            }
            if (userHand.checkForBlackjack()){
                stdout(messageService.blackjackMessage("USER"));
            }
            if (dealerHand.checkForBust()){
                stdout(messageService.bustMessage("DEALER"));
            }
            if (dealerHand.checkForBlackjack()){
                stdout(messageService.blackjackMessage("DEALER"));
            }
        } while(playAgain());

    }

    public Boolean handOver(){
        return userHand.checkForBust() || userHand.checkForBlackjack() || dealerHand.checkForBlackjack() || dealerHand.checkForDealerStand() || dealerHand.checkForBust();
    }

    public void deal(HandService hand){
        hand.addToHand(deckService.popCard());
        hand.tally();
        postDealOutput();
    }

    public Boolean playAgain(){
        String playAgain;
        Scanner sc = new Scanner(System.in);
        do{
            stdout("Another hand? (y/n): ");
            playAgain= sc.next();
        } while(!("y".equalsIgnoreCase(playAgain)) && !("n".equalsIgnoreCase(playAgain)));
        return "y".equalsIgnoreCase(playAgain);
    }

    public void postDealOutput(){
        stdout(messageService.handHeader("DEALER", dealerHand));
        stdout(messageService.handAscii(dealerHand.getHand().getCards()));
        stdout("\n\n" + messageService.handHeader("USER", userHand));
        stdout(messageService.handAscii(userHand.getHand().getCards()));
    }


    public void initialDeal() {
        for (int i = 0; i < 2; i++){
            dealerHand.addToHand(deckService.popCard());
            userHand.addToHand(deckService.popCard());
        }
        dealerHand.getHand().getCards().get(0).setVisible(false);
        dealerHand.tally();
        userHand.tally();
        postDealOutput();
    }


    public Boolean dealAnotherToUser(){
        if(!userHand.getHand().getBust() || !userHand.checkForBlackjack()){
            String userAction;
            Scanner sc = new Scanner(System.in);
            do {
                stdout("Hit or Stay? (h/s): ");
                userAction = sc.next();
            } while(!("h".equalsIgnoreCase(userAction)) && !("s".equalsIgnoreCase(userAction)));
            return "h".equalsIgnoreCase(userAction);
        }
        else {
            return false;
        }
    }

    public Boolean dealAnotherToDealer(){
        return !dealerHand.checkForBlackjack() || !dealerHand.checkForDealerStand() || !dealerHand.getHand().getBust();
    }

    public void stdout(String message){
        // only for cleanliness
        System.out.println(message);
    }

}
