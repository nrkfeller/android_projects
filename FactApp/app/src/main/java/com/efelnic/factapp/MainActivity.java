package com.efelnic.factapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button crazyfactsbutton = (Button) findViewById(R.id.button2);
        crazyfactsbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(MainActivity.this, crazyFacts.class);
                startActivity(intent);
            }

        });

        Button sportfactsbutton = (Button) findViewById(R.id.button);
        sportfactsbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(MainActivity.this, sportFacts.class);
                startActivity(intent);
            }

        });

        Button button_quit = (Button) findViewById(R.id.buttonQuit);
        button_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        final Button mapsButton = (Button) findViewById(R.id.mapsButton);
        mapsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<lat>,<long>?q=<lat>,<long>(Label+Name)"));
                startActivity(intent);
            }

        });

        final Button settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }

        });




    }
}