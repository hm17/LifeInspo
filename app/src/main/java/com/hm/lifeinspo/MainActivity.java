package com.hm.lifeinspo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main Quotes Display
 */
public class MainActivity extends AppCompatActivity {

    private String id;
    private Quote quote = new Quote("Life is what you make of it.", "Unknown");

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionProvider();
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * onClick Method for next button
     * @param view
     */
    public void next(View view) {
        // Use current ID to get next quote from DB
        getQuoteFromDB();
        displayQuote(quote);
    }

    private void setShareActionProvider() {
        String text = "Check out this quote from LifeInspo: \'" + quote + "\'";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    /*
        Update the views with the quote text and author.
     */
    private void displayQuote(Quote quote) {
        TextView quoteView = (TextView) findViewById(R.id.quoteView);
        quoteView.setText(quote.getText());

        TextView authorView = (TextView) findViewById(R.id.authorView);
        authorView.setText(quote.getAuthor());
    }

    /**
     * Populates quote variable with the next quote in DB
     */
    private void getQuoteFromDB() {
        SQLiteOpenHelper lifeInspoDBHelper = new LifeInspoDatabaseHelper(this);
        try {
            SQLiteDatabase db = lifeInspoDBHelper.getReadableDatabase();

            int nextId = quote.getId();

            Cursor cursor = db.query("QUOTE",
                    new String[] {"_id", "QUOTE", "AUTHOR"},
                    "_id = ?",
                    new String[] {Integer.toString(nextId++)},
                    null, null, null);

            // Move to first record in cursor (even if only one)
            if (cursor.moveToFirst()) {
                Quote nextQuote = new Quote();
                nextQuote.setId(Integer.parseInt(cursor.getString(0)));
                nextQuote.setText(cursor.getString(1));
                nextQuote.setAuthor(cursor.getString(2));
                Log.v("getQuoteFromDB", nextQuote.toString());
                quote = nextQuote;

                // TODO: Check if DB is populated b/c only returning the same quote...
            }

            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



}
