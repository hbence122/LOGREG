package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_NAME = "felhasznalo";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "FELHNEV";
    public static final String COL_4 = "JELSZO";
    public static final String COL_5 = "TELJESNEV";



    public dbhelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }



        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String createTables = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_2 + " VARCHAR(50) NOT NULL," +
                    COL_3 + " VARCHAR(50) NOT NULL," +
                    COL_4 + " CARCHAR(20) NOT NULL, " +
                    COL_5 + " VARCHAR(50) NOT NULL)";

            sqLiteDatabase.execSQL(createTables);
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }



    public boolean adatRogzites(String email, String felhasz, String jelszo, String nev)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, felhasz);
        contentValues.put(COL_4, jelszo);
        contentValues.put(COL_5, nev);

        long eredmeny = database.insert(TABLE_NAME, null, contentValues);

        if (eredmeny == -1)
            return false;       //sikertelen felvétel esetén false eredményt kapunk
        else
            return true;        //sikeres felvétel esetén true eredményt kapunk
    }


    public Boolean checkemail(String email){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE " +COL_2+" = ?",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean checkFelhasz(String felhasz){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE " +COL_3+" = ?",new String[]{felhasz});
        if (cursor.getCount()>0) return false;
        else return  true;
    }

    public Boolean checkUser(String email, String felhasz, String password){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE (" +COL_2+" = ? OR "+COL_3+"= ?) AND "+COL_4+"= ?",new String[]{email,felhasz,password});
        if (cursor.getCount()> 0) return true;
        else return false;
    }




}
