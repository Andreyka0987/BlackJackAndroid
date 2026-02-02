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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_DECKS_X = 332;
    public static final int DEFAULT_DECKS_Y = 300;
    private final int PLAYER_CARD_PARAMS_WIDTH = 225;
    private final int PLAYER_CARD_PARAMS_HEIGHT = 370;
     Context mainContext = this;
    ConstraintLayout mainLayout;
    LinearLayout playerDeck;
    LinearLayout dealersDeck;
    Button btnGet;
    Button btnStay;
    Button btnRestart;
    Button btnLeaveToMainMenu;
    SeekBar amountOfMoneyBar;
    ImageView moneyInBtn;
    TextView playerInfoBar;
    TextView winBar;
    TextView balanceView;
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
        dealersDeck = findViewById(R.id.dealers_deck);
        mainLayout = findViewById(R.id.main);

        moneyInBtn = findViewById(R.id.moneyIn_btn);
        btnLeaveToMainMenu = findViewById(R.id.leaveToMainMenu_btn);
        btnRestart = findViewById(R.id.restartGame_btn);
        winBar = findViewById(R.id.winOrLost_text);
        balanceView = findViewById(R.id.money_view);
        amountOfMoneyBar = findViewById(R.id.amoutOfMoney_seekBar);

        btnGet = findViewById(R.id.button2);
        btnStay = findViewById(R.id.button);
        playerInfoBar = findViewById(R.id.textView);


        blackJack = new BlackJack();

        winBar.setVisibility(View.INVISIBLE);
        playerInfoBar.setVisibility(View.INVISIBLE);
        btnRestart.setVisibility(View.INVISIBLE);
        btnLeaveToMainMenu.setVisibility(View.INVISIBLE);

        ImageView secretCardInstance = null;
        if (!blackJack.isGameStarted) {
            blackJack.gameStartDealer();
            blackJack.addSecretCard();
            int[] playerInfoArr = blackJack.gameStartPlayer();
            playerInfoBar.setText(String.valueOf(playerInfoArr[1]));


            ImageView firstCard = new ImageView(this);
            firstCard.setImageResource(playerInfoArr[0]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PLAYER_CARD_PARAMS_WIDTH, PLAYER_CARD_PARAMS_HEIGHT);
            playerDeck.addView(firstCard,params);

           secretCardInstance = animationDealerCard(760f,780f,false);
        }


        // Balance check
        String moneyString;
        try {
            FileInputStream checkIsNullFileInput = openFileInput("money.txt");

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(checkIsNullFileInput));
                    String temp = String.valueOf(bufferedReader.readLine());
                    balanceView.setText(temp);
                    Player.money = Integer.parseInt(temp);
                    checkIsNullFileInput.close();

        } catch (IOException e) {
            // create new instance of file
            FileOutputStream newFileOutPut = null;
            try {
                newFileOutPut = openFileOutput("money.txt",MODE_PRIVATE);

            //entering base balance
            FileInputStream fileInputStream = openFileInput("money.txt");
            InputStreamReader streamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            moneyString = bufferedReader.readLine();


            if (moneyString == null){
                newFileOutPut.write("0".getBytes());
                moneyString = bufferedReader.readLine();
            }

            balanceView.setText(String.valueOf(moneyString));

            Player.money = Integer.parseInt(balanceView.getText().toString());

            newFileOutPut.close();
            fileInputStream.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        }



        // Entering activities
        moneyInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent intent = new Intent(MainActivity.this,MoneyEarningActivity.class);startActivity(intent);}});
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent restartIntent = new Intent(MainActivity.this, MainActivity.class);startActivity(restartIntent);}});
        btnLeaveToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenuIntent = new Intent(MainActivity.this,EntryActivity.class);
                startActivity(mainMenuIntent);}});


        ImageView finalSecretCardInstance = secretCardInstance;
        btnStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInBtn.setVisibility(View.INVISIBLE);

                if (blackJack.isGameStarted) {
                    int  finalAmountOfMoneyThePlayerHasBet = (int) Math.ceil(fractionOfProgressBar()*Player.money);
                    while (true){
                        Cards card = blackJack.getCard();
                        boolean statementOfDealer = blackJack.dealersLogic(card);


                        if (statementOfDealer){
                            animationDealerCard(700f,400f,true);

                            new Handler(Looper.getMainLooper()).postDelayed(new TimerTask() {
                                @Override
                                public void run() {
                                    ImageView cardView = new ImageView(mainContext);
                                    cardView.setImageResource(card.cardID);
                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PLAYER_CARD_PARAMS_WIDTH,PLAYER_CARD_PARAMS_HEIGHT);
                                    dealersDeck.addView(cardView,params);

                                }
                            },660);
                        } else {break;}

                    }

                    new Handler(Looper.getMainLooper()).postDelayed(new TimerTask() {
                        @Override
                        public void run() {
                            finalSecretCardInstance.setImageResource(blackJack.getDEALER().secretCard.cardID);
                        }
                    },1500);



                    new Handler(Looper.getMainLooper()).postDelayed(new TimerTask() {
                        @Override
                        public void run() {
                            btnGet.setVisibility(View.INVISIBLE);
                            btnStay.setVisibility(View.INVISIBLE);
                            winBar.setVisibility(View.VISIBLE);
                            btnRestart.setVisibility(View.VISIBLE);
                            btnLeaveToMainMenu.setVisibility(View.VISIBLE);
                            String result = blackJack.stayLogic();
                            winBar.setText(result);
                            if (result.equals("You won")){
                                Player.money+=finalAmountOfMoneyThePlayerHasBet/2;
                            }
                            if (result.equals("You Lost")) {
                                Player.money -= finalAmountOfMoneyThePlayerHasBet;

                            }
                            try {

                                FileOutputStream fileOutputStream = openFileOutput("money.txt",MODE_PRIVATE);
                                fileOutputStream.write(String.valueOf(Player.money).getBytes());
                                fileOutputStream.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }


                        }
                    },2500);

                }
            }
        });


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (blackJack.isGameStarted) {
                    int[] dataArr = blackJack.getLogic();
                    // return number of players points was used to set label with these

                    if (blackJack.counterOfGottenCards != 10) {
                        ImageView newCard = new ImageView(mainContext);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PLAYER_CARD_PARAMS_WIDTH, PLAYER_CARD_PARAMS_HEIGHT);
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
        card.setX(DEFAULT_DECKS_X);
        card.setY(DEFAULT_DECKS_Y);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(card,"translationY",1650f);
        objectAnimator.setDuration(700);
        objectAnimator.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {card.setVisibility(View.INVISIBLE);}},700);
    }
    public ImageView animationDealerCard(float socketY, float socketX, boolean isNeedToBeHidden){
        ImageView card = new ImageView(mainContext);
        card.setImageResource(R.drawable.back_card_vertical);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(PLAYER_CARD_PARAMS_WIDTH,PLAYER_CARD_PARAMS_HEIGHT);
        mainLayout.addView(card,params);
        card.setX(DEFAULT_DECKS_X+99);
        card.setY(DEFAULT_DECKS_Y);


        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(card,"translationY",socketY);
        objectAnimatorY.setDuration(500);
        objectAnimatorY.start();
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(card,"translationX",socketX);
        objectAnimatorX.setDuration(500);
        objectAnimatorX.start();

        if (isNeedToBeHidden) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    card.setVisibility(View.INVISIBLE);
                }
            }, 650);
        }


        return card;
    }
    public float fractionOfProgressBar(){return (float)amountOfMoneyBar.getProgress()/amountOfMoneyBar.getMax();}



}


