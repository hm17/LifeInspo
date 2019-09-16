package com.hm.lifeinspo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * Main Quotes Display
 */
public class MainActivity extends AppCompatActivity {

    private String quote = "Life is what you make of it.";

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

    private void setShareActionProvider() {
        String text = "Check out this quote from LifeInspo: \'" + quote + "\'";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    /**
     * onClick Method for next button
     * @param view
     */
    public void next(View view) {
        TextView quoteView = (TextView) findViewById(R.id.quoteView);
        quoteView.setText("HELLOOOO");
    }

}
