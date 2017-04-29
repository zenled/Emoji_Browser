package com.kukumoraketo.emojibrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kukumoraketo.emojibrowser.Emoji.EmojiDb.EmojiDb;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        EmojiDb db = new EmojiDb(getApplicationContext());

    }
}
