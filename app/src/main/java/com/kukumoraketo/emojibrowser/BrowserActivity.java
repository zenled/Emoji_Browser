package com.kukumoraketo.emojibrowser;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiTone;
import com.kukumoraketo.emojibrowser.Emoji.EmojiDb.EmojiDb;
import com.kukumoraketo.emojibrowser.Emoji.Providers.All_EmojiLite_Provider;
import com.kukumoraketo.emojibrowser.EmojiDisplay.BasicEmojiDisplayFragment;
import com.kukumoraketo.emojibrowser.EmojiDisplay.EmojiDisplayAdapter;
import com.kukumoraketo.emojibrowser.EmojiDisplay.EmojiDisplayFragment;
import com.kukumoraketo.emojibrowser.EmojiDisplay.EmojiDisplayPagerAdapter;
import com.kukumoraketo.emojibrowser.EmojiDisplay.FragmentType;

public class BrowserActivity extends AppCompatActivity {

    All_EmojiLite_Provider provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        this.provider = new All_EmojiLite_Provider(getApplicationContext(), EmojiTone.TONE_00);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        final EmojiDisplayPagerAdapter pagerAdapter = new EmojiDisplayPagerAdapter(getSupportFragmentManager(), provider, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);


        //region sets TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);




    }
}
