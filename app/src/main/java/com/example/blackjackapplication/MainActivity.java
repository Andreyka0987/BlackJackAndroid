package com.example.blackjackapplication;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private final int CARD_PARAMS_WIDTH = 225;
    private final int CARD_PARAMS_HEIGHT = 370;
    Context mainContext = this;
    ConstraintLayout mainLayout;
    LinearLayout playerDeck;
    Button btnGet;
    Button btnStay;
    Button startGameButton;
    ScrollView deckScroll;

    ImageView newImage;

    TextView info;
    TextView dealerInfo;
    BlackJack blackJack;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        playerDeck = findViewById(R.id.players_deck);
        mainLayout = findViewById(R.id.main);
        startGameButton = findViewById(R.id.button3);
        btnGet = findViewById(R.id.button2);
        btnStay = findViewById(R.id.button);
        info = findViewById(R.id.textView);
        dealerInfo = findViewById(R.id.dealer_score);

        blackJack = new BlackJack();

//        Path path = new Path();

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(diddy,View.X,-100f);
//        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(diddy,View.Y,100f);
//        objectAnimator1.setDuration(2000);
//        objectAnimator.setDuration(2000);
//        objectAnimator1.start();
//        objectAnimator.start();


        if (!blackJack.isGameStarted) {
            int dealersStarterPoints = blackJack.gameStartDealer();
            dealerInfo.setText(String.valueOf(dealersStarterPoints));
            Cards playerCard = blackJack.gameStartPlayer();


            ImageView firstCard = new ImageView(this);
            firstCard.setImageResource(playerCard.cardID);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CARD_PARAMS_WIDTH,CARD_PARAMS_HEIGHT);
            playerDeck.addView(firstCard,params);

        }

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);



                animationPlayerCard(R.drawable.back_card_vertical);

                ImageView newCard = new ImageView(mainContext);
                newCard.setImageResource(R.drawable.efn_k);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(220,370);
                playerDeck.addView(newCard,params);


            }
        });


        btnStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blackJack.isGameStarted) {
                    info.setText(blackJack.stayLogic());
                    String[] infoArr = blackJack.reUpdateStats();
                    dealerInfo.setText(infoArr[1]);
                }
            }
        });


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blackJack.isGameStarted) {
                    int[] dataArr = blackJack.getLogic();
                    boolean ifDeckIsFull = blackJack.IfDeckIsFull;
                    info.setText(String.valueOf(dataArr[0]));

                    if (!ifDeckIsFull) {
                        ImageView newCard = new ImageView(mainContext);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CARD_PARAMS_WIDTH, CARD_PARAMS_HEIGHT);
                        playerDeck.addView(newCard, params);

                        animationPlayerCard(R.drawable.back_card);

                        new Handler(Looper.getMainLooper()).postDelayed(() -> {
                            newCard.setImageResource(dataArr[1]);
                        }, 700);
                    }





                }
            }
        });



    }
    public void animationPlayerCard(int cardID){
        ImageView card = new ImageView(mainContext);
        card.setImageResource(cardID);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(420,400);
        mainLayout.addView(card,params);
        card.setX(332);
        card.setY(300);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(card,"translationY",1650f);
        objectAnimator.setDuration(700);
        objectAnimator.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {card.setVisibility(View.INVISIBLE);}},700);
    }




}


