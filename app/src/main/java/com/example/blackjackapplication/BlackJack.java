package com.example.blackjackapplication;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class BlackJack {

    boolean isGameStarted = false;
    private  Dealer DEALER = new Dealer();
    private  Player PLAYER = new Player();
    static final ArrayList<Cards> deck = new ArrayList<>();

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
        DEALER.points = 0;
        PLAYER.points = 0;
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

    int counterOfGottenCards = 0;
    public int[] getLogic(){
        if (isGameStarted) {
            if (counterOfGottenCards != 10) {
                counterOfGottenCards++;
                Cards card = getCard();
                PLAYER.points += card.value;
                IfDeckIsFull = false;

                return new int[]{PLAYER.points, card.cardID};
            }
        }
        return null;
    }

    public Player getPLAYER() {return PLAYER;}
    public Dealer getDEALER() {return DEALER;}
}
