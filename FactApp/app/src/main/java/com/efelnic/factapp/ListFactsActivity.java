package com.efelnic.factapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFactsActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Switch crazySwitch;
    Switch sportSwitch;
    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_facts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myDb = new DatabaseHelper(this);
        crazySwitch = (Switch)findViewById(R.id.crazySwitch);
        sportSwitch = (Switch)findViewById(R.id.sportSwitch);
        goButton = (Button)findViewById(R.id.goButton);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void viewDatabase(View view) {

        ListView myListView = (ListView) findViewById(R.id.listView);

        final ArrayList<String> databaseEntries = new ArrayList<String>();


        try {
            Cursor res = myDb.getAllData();
            if ( res.getCount() == 0 ) {
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                if ( crazySwitch.isChecked() ){
                    if ( res.getString(1).equals("crazy")) {
                        databaseEntries.add(res.getString(2));
                    }
                }

                if ( sportSwitch.isChecked() ) {
                    if ( res.getString(1).equals("sport")) {
                        databaseEntries.add(res.getString(2));
                    }
                }

                //databaseEntries.add(res.getString(2));


                //buffer.append("ID : " + res.getString(0) + "\n");
                //buffer.append("Type : " + res.getString(1) + "\n");
                //buffer.append("Fact : " + res.getString(2) + "\n\n");
            }
        } catch (Exception e) {
            Toast.makeText(ListFactsActivity.this, "Database is Empty", Toast.LENGTH_LONG).show();
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, databaseEntries);

        // set the adapter to the list view
        myListView.setAdapter(arrayAdapter);
    }



}
