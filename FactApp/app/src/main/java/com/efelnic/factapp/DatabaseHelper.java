package com.efelnic.factapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by efelnic on 2/28/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "fact.db";
    public static final String TABLE_NAME = "fact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TYPE";
    public static final String COL_3 = "FACT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT, FACT TEXT)");

    }

    public void createDB() {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT, FACT TEXT)");
    }

    public boolean insertData(String type, String fact){

        SQLiteDatabase db = this.getReadableDatabase();

        if ( type.equals("sport") || type.equals("crazy")) {

            Log.i("saving", type);

            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_2, type);
            contentValues.put(COL_3, fact);

            long result = db.insert(TABLE_NAME, null, contentValues);

            if ( result == -1 ){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }



    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);

        return result;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        return db.delete(TABLE_NAME, "ID = ?", new String[] { id });

    }

    public void deleteEverything(){
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
