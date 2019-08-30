package com.hm.lifeinspo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Main Quotes Display
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
