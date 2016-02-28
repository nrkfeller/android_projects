package com.efelnic.factapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class sportFacts extends AppCompatActivity {

    String[] sFacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_facts);

        sFacts = getResources().getStringArray(R.array.sFacts);
        TextView nameText = (TextView) findViewById(R.id.nameText);

        Button sportFactsGenButton = (Button) findViewById(R.id.button4);
        final TextView textView2 = (TextView)
                findViewById(R.id.sportView);

        sportFactsGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateText();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String a = sp.getString("example_text", "test string");

        nameText.setText(a);
    }
    public void updateText() {
        FactsModel factsModel = new FactsModel();
        final TextView textView = (TextView) findViewById(R.id.sportView);
        textView.setText( factsModel.getFacts(sFacts));
    }



}
