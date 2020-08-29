package com.example.martialartclub.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.martialartclub.Model.MartialArt;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="martialArtsDatabase";
    private static final int DATABASE_VERSION=1;
    private static final String MARTIAL_ART_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
     private static final String NAME_KEY="name";
    private static final String PRICE_KEY="price";
    private static final String COLOR_KEY="color";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createDatabaseSQL="create table " + MARTIAL_ART_TABLE +
                "( " + ID_KEY + " integer primary key autoincrement" +
                ", " + NAME_KEY + " text" + ", " + PRICE_KEY + " real" +
                "," + COLOR_KEY + " text" + " )";
        sqLiteDatabase.execSQL(createDatabaseSQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists " + MARTIAL_ART_TABLE);
            onCreate(sqLiteDatabase);
    }

    public void addMartialArt(MartialArt martialArtObject){
        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtSQLCommand = "insert into " + MARTIAL_ART_TABLE +
                                         " values(null, '" + martialArtObject.getMartialArtName()
                                         + "', '" +martialArtObject.getMartialArtPrice()
                                         + "', '" +martialArtObject.getMartialArtColor()
                                         + "') ";

        database.execSQL(addMartialArtSQLCommand);
        database.close();
    }
    public void deleteMartialArtObjectFromDatabaseById(int id){

        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtSQLCommand = "delete from " + MARTIAL_ART_TABLE
                                            + " where " + ID_KEY + " = " + id;
        database.execSQL(deleteMartialArtSQLCommand);
        database.close();
    }
    public void modifyMartialArtObject(int martialArtID, String martialArtName,
                                       double martialArtPrice,String martialArtColor){
        SQLiteDatabase database = getWritableDatabase();
        String modifyMartialArtSQLCommand = "update " + MARTIAL_ART_TABLE +
                                            " set " + NAME_KEY + " = '" + martialArtName
                                            + "', " +PRICE_KEY + " = '" + martialArtPrice
                                            + "', " + COLOR_KEY + " = '" + martialArtColor
                                            + "' " + "where " +ID_KEY + " = " +martialArtID;
        database.execSQL(modifyMartialArtSQLCommand);
        database.close();


    }
    public ArrayList<MartialArt> returnAllMartialArtObjects() {
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " +MARTIAL_ART_TABLE;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);
        ArrayList<MartialArt> martialArts = new ArrayList<>();

        while (cursor.moveToNext()){
            MartialArt currentMartialObject = new MartialArt(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),cursor.getDouble(2),cursor.getString(3));
            martialArts.add(currentMartialObject);
        }
        database.close();
        return martialArts;
    }
    public MartialArt returnMartialArtObjectByID(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sqlQueryCommand = "select * from " + MARTIAL_ART_TABLE +
                                 " where " + ID_KEY + " = " +id;
        Cursor cursor = database.rawQuery(sqlQueryCommand, null);
        MartialArt martialArtObject = null;
        if(cursor.moveToFirst()){
            martialArtObject = new MartialArt(Integer.parseInt(cursor.getString(0)),
                                                cursor.getString(1),cursor.getDouble(2),
                                                cursor.getString(3));

        }
        database.close();
        return martialArtObject;
    }
}
