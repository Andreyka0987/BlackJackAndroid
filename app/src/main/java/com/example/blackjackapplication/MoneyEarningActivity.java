package com.example.blackjackapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MoneyEarningActivity extends AppCompatActivity {

    ImageView backButton;
    Button earnMoney;
    TextView balanceView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_money_earning);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        backButton = findViewById(R.id.backToGame_btn);
        earnMoney = findViewById(R.id.money_btn);
        balanceView = findViewById(R.id.balance_view);

        try {
            FileInputStream startBalance = openFileInput("money.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(startBalance);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String tempText = bufferedReader.readLine();
            balanceView.setText(tempText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent intent = new Intent(MoneyEarningActivity.this,MainActivity.class);startActivity(intent);}});

        earnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Player.money >= 20) {
                    earnMoney.setActivated(false);
                } else {
                    earnMoney.setActivated(true);

                    String moneyInstance = "";
                    try {
                        FileInputStream fileInputStream = openFileInput("money.txt");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        moneyInstance = String.valueOf(bufferedReader.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    try {
                        FileOutputStream fileOutputStream = openFileOutput("money.txt", MODE_PRIVATE);
                        int money = Integer.parseInt(moneyInstance);
                        money++;
                        Player.money = money;
                        fileOutputStream.write(String.valueOf(money).getBytes());
                        balanceView.setText(String.valueOf(money));
                        fileOutputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });




    }




}