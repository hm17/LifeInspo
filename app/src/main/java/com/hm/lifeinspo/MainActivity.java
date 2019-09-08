package com.hm.lifeinspo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;

/**
 * Main Quotes Display
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
