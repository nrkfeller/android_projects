package com.efelnic.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Number {
        int number;

        public boolean isTriangular() {
            int x = 1;
            int triangularNumber = 1;
            while (triangularNumber < number) {
                x++;
                triangularNumber = triangularNumber + x;
            }

            if (triangularNumber == number) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isSquare() {
            double squareRoot = Math.sqrt(number);

            if (squareRoot == Math.floor(squareRoot)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void checkNumber(View view){

        EditText number = (EditText) findViewById(R.id.usersNumber);

        String message = "";

        if (number.getText().toString().isEmpty()){

            message = "Enter a Number!";

        } else {
            
            Number myNumber = new Number();

            myNumber.number = Integer.parseInt(number.getText().toString());


            if (myNumber.isSquare() && myNumber.isTriangular()){
                message = "Triangular and Square";
            } else if (myNumber.isSquare()){
                message = "Is Only Square";
            } else if (myNumber.isTriangular()){
                message = "Is Only Triangular";
            } else {
                message = "Is neither";
            }
        }

        Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG ).show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
