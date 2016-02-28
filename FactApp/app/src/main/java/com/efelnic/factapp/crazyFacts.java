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

public class crazyFacts extends AppCompatActivity {

    String[] cFacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crazy_facts);
        TextView nameText = (TextView)findViewById(R.id.nameTextCrazy);

        cFacts = getResources().getStringArray(R.array.cFacts);

        Button crazyFactsGenButton = (Button) findViewById(R.id.button5);

        final TextView textView3 = (TextView)
                findViewById(R.id.crazyView);

        crazyFactsGenButton.setOnClickListener(new View.OnClickListener() {
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
        String userNameFromPreferences = sp.getString("example_text", "test string");

        nameText.setText(userNameFromPreferences);
    }


    public void updateText() {
        FactsModel factsModel = new FactsModel();
        final TextView textView = (TextView) findViewById(R.id.crazyView);
        textView.setText( factsModel.getFacts(cFacts));
    }


}
