package com.kukumoraketo.emojibrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kukumoraketo.emojibrowser.Emoji.EmojiDb.EmojiDb;
import com.kukumoraketo.emojibrowser.Emoji.Providers.All_EmojiLite_Provider;

public class BrowserActivity extends AppCompatActivity {

    All_EmojiLite_Provider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        this.provider = new All_EmojiLite_Provider(getApplicationContext());

        int a = 1;

    }
}
