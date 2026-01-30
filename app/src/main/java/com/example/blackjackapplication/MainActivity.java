package com.example.blackjackapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnGet;
    Button btnStay;
    Button startGameButton;


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
            String[] infoArr = blackJack.gameStart();
            info.setText(infoArr[0]);
            dealerInfo.setText(infoArr[1]);
        }

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!blackJack.isGameStarted) {
                    String[] infoArr = blackJack.gameStart();
                    info.setText(infoArr[0]);
                    dealerInfo.setText(infoArr[1]);

                }
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
                    info.setText(blackJack.getLogic());
                }
            }
        });



    }




}


