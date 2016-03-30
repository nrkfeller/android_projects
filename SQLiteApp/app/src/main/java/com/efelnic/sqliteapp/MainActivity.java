package com.efelnic.sqliteapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText nameText, surnameText, marksText, idText;
    Button addButton;
    Button viewDataButton;
    Button updateDataButton;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        nameText = (EditText) findViewById(R.id.nameText);
        surnameText = (EditText) findViewById(R.id.surnameText);
        marksText = (EditText) findViewById(R.id.markText);
        addButton = (Button) findViewById(R.id.addButton);
        viewDataButton = (Button) findViewById(R.id.viewAllButton);
        updateDataButton = (Button) findViewById(R.id.updateDataButton);
        idText = (EditText) findViewById(R.id.idText);
        deleteButton = (Button)findViewById(R.id.deleteButton);

        addData();
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void DeleteData(){
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(idText.getText().toString());

                        if (deletedRows > 0 ){
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Nothing to delete", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void UpdateData() {
        updateDataButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(idText.getText().toString(), nameText.getText().toString(), surnameText.getText().toString(), marksText.getText().toString());

                        if ( isUpdated == true ) {
                            Toast.makeText(MainActivity.this, "Student data Updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Student data is not updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void addData(){
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(nameText.getText().toString(), surnameText.getText().toString(), marksText.getText().toString());

                        if (isInserted == true) {
                            Toast.makeText(MainActivity.this, "Student data commited", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Not commited", Toast.LENGTH_LONG).show();
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
                            buffer.append("NAME : " + res.getString(1) + "\n");
                            buffer.append("SURNAME : " + res.getString(2) + "\n");
                            buffer.append("MARKS : " + res.getString(3) + "\n\n");
                        }

                        showMessage("Data", buffer.toString());
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
