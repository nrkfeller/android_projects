package com.efelnic.timerdemo;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000){

            // onTick activates once every 10000/1000
            public void onTick(long milliseconduntildone){
                Log.i("Seconds Left : ", String.valueOf((milliseconduntildone/1000)));
            }

            // once the timer is done
            public void onFinish(){
                Log.i("Done!", "Countdown finished");
            }
        }.start();



        /*
        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {

                Log.i("runnable has run!", "one second has passsed");
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(run);
        */
    }
}
