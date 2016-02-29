package com.efelnic.factapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insertFactToDatabase extends AppCompatActivity {

    DatabaseHelper myDb;
    Button addFact;
    Button viewDataButton;
    Button deleteFact;
    EditText factTypeText;
    EditText factText;
    EditText deleteIDText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_fact_to_database);

        myDb = new DatabaseHelper(this);
        addFact = (Button) findViewById(R.id.addFactButton);
        deleteFact = (Button) findViewById(R.id.deleteFactButton);
        viewDataButton = (Button) findViewById(R.id.viewDataButton);
        factText = (EditText)findViewById(R.id.factText);
        factTypeText = (EditText)findViewById(R.id.factTypeText);
        deleteIDText = (EditText)findViewById(R.id.deleteIDText);

        addData();
        viewAll();
        DeleteData();
    }

    public void addData(){
        addFact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(factTypeText.getText().toString(), factText.getText().toString());
                        if (isInserted == true) {
                            Toast.makeText(insertFactToDatabase.this, "Fact has been added!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(insertFactToDatabase.this, "Invalid type of Database failure", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewAll(){
        viewDataButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if ( res.getCount() == 0 ) {
                            showMessage("Error", "database empty");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID : " + res.getString(0) + "\n");
                            buffer.append("Type : " + res.getString(1) + "\n");
                            buffer.append("Fact : " + res.getString(2) + "\n\n");
                        }

                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void DeleteData(){
        deleteFact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(deleteIDText.getText().toString());

                        if (deletedRows > 0 ){
                            Toast.makeText(insertFactToDatabase.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(insertFactToDatabase.this, "Nothing to delete", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
