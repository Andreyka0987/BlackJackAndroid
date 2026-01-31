package com.example.blackjackapplication;

import java.util.ArrayList;
import java.util.Random;

public class BlackJack {

    boolean isGameStarted = false;
    private final Dealer DEALER = new Dealer();
    private final Player PLAYER = new Player();
    static ArrayList<Cards> deck = new ArrayList<>();

    public BlackJack(){reClearStats();}



    public int gameStartDealer(){
        reClearStats();
        isGameStarted = true;
        DEALER.secretCard = getCard();
        DEALER.points+=getCard().value;
        return DEALER.points;
    }
    public Cards gameStartPlayer(){
        if (isGameStarted){
            Cards firstCard = getCard();
            return firstCard;
        }
        return null;
    }

    private void reClearStats(){
        Cards.init();
        DEALER.secretCard = getCard();
        DEALER.points = 0;
        PLAYER.points = 0;
    }
    public String[] reUpdateStats(){
        return new String[]{String.valueOf(PLAYER.points),String.valueOf(DEALER.points)};


    }

    private Cards getCard(){
        Random random = new Random();
        int cardIndex = random.nextInt(deck.size());
        Cards returnCard = deck.get(cardIndex);
        deck.remove(cardIndex);
        return returnCard;
    }

    public String stayLogic(){

        isGameStarted = false;
        DEALER.points += DEALER.secretCard.value;

        if (DEALER.points > PLAYER.points || PLAYER.points > 21 && PLAYER.points > DEALER.points) {
            return "You Lost";
        }
        if (DEALER.points < PLAYER.points) {
            return "You won";
        }
        reClearStats();

        return "draw";


    }


    boolean IfDeckIsFull;
    public int[] getLogic(){
        if (isGameStarted) {
            Random random = new Random(3);
            if (random.nextInt() > 3) {
                DEALER.points += getCard().value;
            }
            Cards card = getCard();

            if (PLAYER.points <= 21) {
                PLAYER.points += card.value;
                IfDeckIsFull = false;
            }
            else {
                IfDeckIsFull = true;
            }
            return new int[]{PLAYER.points,card.cardID};
        }
        return null;
    }









}
