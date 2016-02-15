package com.efelnic.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    boolean gameActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] gameWon = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000f);



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
        }

        counter.animate().translationYBy(1000f).rotation(180).setDuration(300);

        for (int[] winningPosition : gameWon){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                gameActive = false;
                String winner = "Red";

                if (gameState[winningPosition[0]] == 0){
                    winner = "Yellow";
                }

                System.out.println(gameState[winningPosition[0]]);

                TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);

                winnerMessage.setText(winner + " has won!");

                RelativeLayout layout = (RelativeLayout)findViewById(R.id.playAgainLayout);

                layout.setVisibility(VISIBLE);
            } else {
                boolean gameOver = true;
                for (int counterState : gameState){
                    if (counterState ==2) gameOver = false;
                }

                if (gameOver) {
                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);

                    winnerMessage.setText("No one has won!");

                    RelativeLayout layout = (RelativeLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(VISIBLE);
                }
            }
        }

    }

    public void playAgain(View view) {
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);

        for (int i = 0; i < grid.getChildCount(); i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
