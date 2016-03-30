package com.efelnic.horl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int randomNumber;

    public void checkGuess(View view) {

        EditText guessedNumber = (EditText) findViewById(R.id.guessedNumber); // takes it in as guessedNumber

        String guessedNumberString = guessedNumber.getText().toString(); // changes guessed number to a string

        int guessedNumberInt = Integer.parseInt(guessedNumberString);

        String message = "";

        if (guessedNumberInt > randomNumber) {
            message =  "Too High";
        } else if (guessedNumberInt < randomNumber) {
            message = "Too Low";
        } else {
            message = "Correct!";
            Random randomGenerator = new Random();
            randomNumber = randomGenerator.nextInt(21);
        }


        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

        // System.out.println(randomNumber); // Displays on Log file



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random randomGenerator = new Random();

        randomNumber = randomGenerator.nextInt(21);

    }
}
