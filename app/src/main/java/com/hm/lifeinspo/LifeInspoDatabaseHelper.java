package com.hm.lifeinspo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LifeInspoDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "lifeinspo";
    private static final int DB_VERSION = 1;

    LifeInspoDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertQuote(SQLiteDatabase db, String quote, String author) {
        ContentValues quoteValues = new ContentValues();
        quoteValues.put("QUOTE", quote);
        quoteValues.put("AUTHOR", author);
        db.insert("QUOTE", null, quoteValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE QUOTE (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "QUOTE TEXT, "
                    + "AUTHOR TEXT, "
                    + "FAVORITE NUMERIC);");

            insertQuote(db, "You make my dopamine levels go all silly.", "Author unknown");
            insertQuote(db, "Just say yes.", "Author unknown");
            insertQuote(db, "When you are stuck in a place where you feel you are not good enough, remember that what is right with you is always more than what is wrong with you.", "Author unknown");

        }

        if (oldVersion < 2) {
            // Upgrade code goes here
        }
    }

}
