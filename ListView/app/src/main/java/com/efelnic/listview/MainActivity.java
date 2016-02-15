package com.efelnic.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.listView);

        // create an array list
        final ArrayList<String> countries = new ArrayList<String>();

        countries.add("Jamaica");
        countries.add("Switzerland");
        countries.add("Austria");
        countries.add("New Zeland");
        countries.add("Venezuela");


        // create an adapter for the array list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);

        // set the adapter to the list view
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "I am going to " + countries.get(position), Toast.LENGTH_LONG).show();
            }
        });



    }
}
