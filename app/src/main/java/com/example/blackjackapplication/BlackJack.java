package com.example.blackjackapplication;

import java.util.ArrayList;
import java.util.Random;

public class BlackJack {

    boolean isGameStarted = false;
    private final Dealer DEALER = new Dealer();
    private final Player PLAYER = new Player();
    static ArrayList<Cards> deck = new ArrayList<>();

    public BlackJack(){reClearStats();}



    public void gameStartDealer(){
        reClearStats();
        isGameStarted = true;
        DEALER.secretCard = getCard();
    }
    public void addSecretCard(){DEALER.points+=DEALER.secretCard.value;}
    public int[] gameStartPlayer(){
        if (isGameStarted){
            Cards firstCard = getCard();
            PLAYER.points += firstCard.value;
            return new int[] {firstCard.cardID,PLAYER.points};
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

    public Cards getCard(){
        Random random = new Random();
        int cardIndex = random.nextInt(deck.size());
        Cards returnCard = deck.get(cardIndex);
        deck.remove(cardIndex);
        return returnCard;
    }

    public String stayLogic(){

        isGameStarted = false;


        if (DEALER.points > PLAYER.points && PLAYER.points <= 21
                && DEALER.points <=21 || PLAYER.points > 21 && DEALER.points < 21 ||
                PLAYER.points > 21 && DEALER.points > 21 && DEALER.points < PLAYER.points) {
            return "You Lost";
        }
        if (DEALER.points < PLAYER.points  && PLAYER.points <= 21 || PLAYER.points < 21 && DEALER.points > 21 ||
                PLAYER.points > 21 && DEALER.points > 21 && DEALER.points > PLAYER.points) {
            return "You won";
        }
        reClearStats();

        return "draw";

    }
    public boolean dealersLogic(Cards cards){
        boolean isGotACard = false;

        if (DEALER.points >= 15 && DEALER.points+cards.value <= 21){
            Random random = new Random();
            int randomNum = random.nextInt(100);
            if (randomNum <= 10){
                DEALER.points +=cards.value;
                isGotACard = true;
            }

        }
        if (DEALER.points == 14 && DEALER.points+cards.value <= 21){
            Random random = new Random();
            int randomNum = random.nextInt(100);
            if (randomNum <= 30){
                DEALER.points +=cards.value;
                isGotACard = true;
            }

        }
        if (DEALER.points == 13 && DEALER.points+cards.value <= 21){
            Random random = new Random();
            int randomNum = random.nextInt(100);
            if (randomNum <= 50){
                DEALER.points +=cards.value;
                isGotACard = true;
            }
        }
        if (DEALER.points == 12){
            Random random = new Random();
            int randomNum = random.nextInt(100);
            if (randomNum <= 80){
                DEALER.points +=cards.value;
                isGotACard = true;
            }
        }
        if (DEALER.points <= 11){
            DEALER.points+=cards.value;
            isGotACard = true;
        }


        return isGotACard;
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

    public Dealer getDEALER() {return DEALER;}
}
