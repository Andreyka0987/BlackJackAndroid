package com.example.blackjackapplication;

import java.util.ArrayList;

public class Cards {
    String name;
    int value;
    public Cards(String name, int value){
        this.name = name;
        this.value = value;
    }


    public static void init(){

        Cards clubsTwo = new Cards("clubs_two",2);
        Cards clubsThree = new Cards("clubs_three",3);
        Cards clubsFour = new Cards("clubs_four",4);
        Cards clubsFive = new Cards("clubs_five",5);
        Cards clubsSix = new Cards("clubs_six",6);
        Cards clubsSeven = new Cards("clubs_seven",7);
        Cards clubsEight = new Cards("clubs_eight",8);
        Cards clubsNine = new Cards("clubs_nine",9);
        Cards clubsTen = new Cards("clubs_ten",10);
        Cards clubsJ = new Cards("clubs_J",10);
        Cards clubsQ = new Cards("clubs_Q",10);
        Cards clubsK = new Cards("clubs_K",10);
        Cards clubsAce = new Cards("clubs_ace",10);
        BlackJack.deck.add(clubsTwo);BlackJack.deck.add(clubsThree);BlackJack.deck.add(clubsFour);
        BlackJack.deck.add(clubsFive);BlackJack.deck.add(clubsSix);BlackJack.deck.add(clubsSeven);
        BlackJack.deck.add(clubsEight);BlackJack.deck.add(clubsNine);BlackJack.deck.add(clubsTen);
        BlackJack.deck.add(clubsJ);BlackJack.deck.add(clubsQ);BlackJack.deck.add(clubsK);
        BlackJack.deck.add(clubsAce);


        Cards diamondTwo = new Cards("diamond_two",2);
        Cards diamondThree = new Cards("diamond_three",3);
        Cards diamondFour = new Cards("diamond_four",4);
        Cards diamondFive = new Cards("diamond_five",5);
        Cards diamondSix = new Cards("diamond_six",6);
        Cards diamondSeven = new Cards("diamond_seven",7);
        Cards diamondEight = new Cards("diamond_eight",8);
        Cards diamondNine = new Cards("diamond_nine",9);
        Cards diamondTen = new Cards("diamond_ten",10);
        Cards diamondJ = new Cards("diamond_J",10);
        Cards diamondQ = new Cards("diamond_Q",10);
        Cards diamondK = new Cards("diamond_K",10);
        Cards diamondAce = new Cards("diamond_ace",10);
        BlackJack.deck.add(diamondTwo);BlackJack.deck.add(diamondThree);BlackJack.deck.add(diamondFour);
        BlackJack.deck.add(diamondFive);BlackJack.deck.add(diamondSix);BlackJack.deck.add(diamondSeven);
        BlackJack.deck.add(diamondEight);BlackJack.deck.add(diamondNine);BlackJack.deck.add(diamondTen);
        BlackJack.deck.add(diamondJ);BlackJack.deck.add(diamondQ);BlackJack.deck.add(diamondK);
        BlackJack.deck.add(diamondAce);

        Cards heartsTwo = new Cards("hearts_two",2);
        Cards heartsThree = new Cards("hearts_three",3);
        Cards heartsFour = new Cards("hearts_four",4);
        Cards heartsFive = new Cards("hearts_five",5);
        Cards heartsSix = new Cards("hearts_six",6);
        Cards heartsSeven = new Cards("hearts_seven",7);
        Cards heartsEight = new Cards("hearts_eight",8);
        Cards heartsNine = new Cards("hearts_nine",9);
        Cards heartsTen = new Cards("hearts_ten",10);
        Cards hearts_J = new Cards("hearts_J",10);
        Cards hearts_Q = new Cards("hearts_Q",10);
        Cards hearts_K = new Cards("hearts_K",10);
        Cards heartsAce = new Cards("hearts_ace",10);
        BlackJack.deck.add(heartsTwo);BlackJack.deck.add(heartsThree);BlackJack.deck.add(heartsFour);
        BlackJack.deck.add(heartsFive);BlackJack.deck.add(heartsSix);BlackJack.deck.add(heartsSeven);
        BlackJack.deck.add(heartsEight);BlackJack.deck.add(heartsNine);BlackJack.deck.add(heartsTen);
        BlackJack.deck.add(hearts_J);BlackJack.deck.add(hearts_Q);BlackJack.deck.add(hearts_K);
        BlackJack.deck.add(heartsAce);

        Cards spadesTwo = new Cards("spades_two",2);
        Cards spadesThree = new Cards("spades_three",3);
        Cards spadesFour = new Cards("spades_four",4);
        Cards spadesFive = new Cards("spades_five",5);
        Cards spadesSix = new Cards("spades_six",6);
        Cards spadesSeven = new Cards("spades_seven",7);
        Cards spadesEight = new Cards("spades_eight",8);
        Cards spadesNine = new Cards("spades_nine",9);
        Cards spadesTen = new Cards("spades_ten",10);
        Cards spadesJ = new Cards("spades_J",10);
        Cards spadesQ = new Cards("spades_Q",10);
        Cards spadesK = new Cards("spades_K",10);
        Cards spadesAce = new Cards("spades_ace",2);
        BlackJack.deck.add(spadesTwo);BlackJack.deck.add(spadesThree);BlackJack.deck.add(spadesFour);
        BlackJack.deck.add(spadesFive);BlackJack.deck.add(spadesSix);BlackJack.deck.add(spadesSeven);
        BlackJack.deck.add(spadesEight);BlackJack.deck.add(spadesNine);BlackJack.deck.add(spadesTen);
        BlackJack.deck.add(spadesJ);BlackJack.deck.add(spadesQ);BlackJack.deck.add(spadesK);
        BlackJack.deck.add(spadesAce);

    }


}
