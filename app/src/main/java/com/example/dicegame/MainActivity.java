package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView dice1, dice2, dice3, dice4, dice5;
    private TextView rollResultTextView, gameScoreTextView, rollCountTextView;
    private Button rollButton, resetButton;

    private int gameScore = 0;
    private int rollCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja komponentów
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);
        rollResultTextView = findViewById(R.id.rollResultTextView);
        gameScoreTextView = findViewById(R.id.gameScoreTextView);
        rollCountTextView = findViewById(R.id.rollCountTextView);
        rollButton = findViewById(R.id.rollButton);
        resetButton = findViewById(R.id.resetButton);

        // Ustawienie listenerów przycisków
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        // Losowanie pięciu kości
        Random random = new Random();
        int[] diceResults = new int[5];
        int[] countResults = new int[6]; // Do zliczania ilości wystąpień poszczególnych wartości kości
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            diceResults[i] = random.nextInt(6) + 1; // Losuje liczby od 1 do 6
            countResults[diceResults[i] - 1]++; // Zlicza ile razy wylosowano każdą wartość
        }

        // Wyświetlanie wyników kostek
        dice1.setText(String.valueOf(diceResults[0]));
        dice2.setText(String.valueOf(diceResults[1]));
        dice3.setText(String.valueOf(diceResults[2]));
        dice4.setText(String.valueOf(diceResults[3]));
        dice5.setText(String.valueOf(diceResults[4]));

        // Sumowanie tylko tych liczb, które wystąpiły co najmniej dwa razy
        for (int i = 0; i < 6; i++) {
            if (countResults[i] >= 2) {
                sum += (i + 1) * countResults[i]; // Sumuje wartości
            }
        }

        // Wyświetlanie wyniku rzutu
        rollResultTextView.setText("Wynik tego losowania: " + sum);
        updateScore(sum);
        updateRollCount();
    }

    private void resetGame() {
        // Resetowanie wyników
        dice1.setText("?");
        dice2.setText("?");
        dice3.setText("?");
        dice4.setText("?");
        dice5.setText("?");

        gameScore = 0;
        rollCount = 0;
        rollResultTextView.setText("Wynik tego losowania: 0");
        gameScoreTextView.setText("Wynik gry: 0");
        rollCountTextView.setText("Liczba rzutów: 0");
    }

    private void updateScore(int newScore) {
        gameScore += newScore;
        gameScoreTextView.setText("Wynik gry: " + gameScore);
    }

    private void updateRollCount() {
        rollCount++;
        rollCountTextView.setText("Liczba rzutów: " + rollCount);
    }
}
