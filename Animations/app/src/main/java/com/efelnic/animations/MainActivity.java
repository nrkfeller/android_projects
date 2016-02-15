package com.efelnic.animations;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView turnip = (ImageView) findViewById(R.id.turnipView);
        turnip.setTranslationX(2000f);
    }

    public void fade(View view) {
        ImageView potato = (ImageView) findViewById(R.id.potatoView);
        ImageView turnip = (ImageView) findViewById(R.id.turnipView);

        potato.animate().translationYBy(1500f).setDuration(2000);

        turnip.animate().alpha(1f).setDuration(2000);

        turnip.animate().translationXBy(-2000f);

        turnip.animate().rotation(180f).setDuration(2000);

        turnip.animate().scaleX(0.5f).scaleY(0.5f).setDuration(4000);

    }
}
