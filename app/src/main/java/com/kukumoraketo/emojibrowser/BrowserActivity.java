package com.kukumoraketo.emojibrowser;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiTone;
import com.kukumoraketo.emojibrowser.Emoji.EmojiDb.EmojiDb;
import com.kukumoraketo.emojibrowser.Emoji.Providers.All_EmojiLite_Provider;
import com.kukumoraketo.emojibrowser.EmojiDisplay.BasicEmojiDisplayFragment;
import com.kukumoraketo.emojibrowser.EmojiDisplay.ChangeToneDialogFragment;
import com.kukumoraketo.emojibrowser.EmojiDisplay.EmojiDisplayAdapter;
import com.kukumoraketo.emojibrowser.EmojiDisplay.EmojiDisplayFragment;
import com.kukumoraketo.emojibrowser.EmojiDisplay.EmojiDisplayPagerAdapter;
import com.kukumoraketo.emojibrowser.EmojiDisplay.FragmentType;

public class BrowserActivity extends AppCompatActivity implements ChangeToneDialogFragment.ChangeToneListener,
                                                                   IActivitiContainingSearchEmojiFragment{

    private All_EmojiLite_Provider provider;
    private EmojiDisplayPagerAdapter pagerAdapter;

    private MenuItem toneIndicator;

    private InputMethodManager inputMethodManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        this.provider = new All_EmojiLite_Provider(getApplicationContext(), EmojiTone.TONE_00);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new EmojiDisplayPagerAdapter(getSupportFragmentManager(), provider, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setCurrentItem(1);


        //region sets TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        //endregion

        //region sets Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){

                    case R.id.mi_change_tone: {
                        ChangeToneDialogFragment changeToneDialog = ChangeToneDialogFragment.newInstance(provider.getTone());
                        changeToneDialog.show(getSupportFragmentManager(), "change_tone_dialog");
                        return true;
                    }
                    case R.id.mi_about: {
                        AboutDialogFragment aboutDialog = AboutDialogFragment.newInstance();
                        aboutDialog.show(getSupportFragmentManager(), "about_dialog");
                        return true;
                    }
                    default:
                        break;
                }

                return true;
            }
        });

        //endregion

        // sets InputMethodManager
        this.inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //region sets that Keyboard hides when changing tabs
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                View focus = getCurrentFocus();
                if (focus != null){
                    hideKeyboard(focus);
                }
            }

            @Override
            public void onPageSelected(int position) {
                View focus = getCurrentFocus();
                if (focus != null){
                    hideKeyboard(focus);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                View focus = getCurrentFocus();
                if (focus != null){
                    hideKeyboard(focus);
                }
            }
        });
        //endregion

        getWindow().setBackgroundDrawableResource(R.color.color_material_gray_light);
    }

    @Override
    public void onChangeToneDialogPositiveClick(EmojiTone selectedTone) {

        this.provider.setTone(selectedTone);
        this.updateToneIndicator();

        // Do this if tone changed
        for (EmojiDisplayFragment fragment: pagerAdapter.fragments){
            if (fragment != null) {
                fragment.forceEmojiRefresh();
                fragment.forceGridViewRefresh();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_emoji_display, menu);

        this.toneIndicator =  menu.findItem(R.id.mi_change_tone);
        updateToneIndicator();

        return true;
    }

    private void updateToneIndicator(){
        this.toneIndicator.setIcon(EmojiTone.getIcon(this.provider.getTone()));
    }

    @Override
    public void hideKeyboard(View v) {
        this.inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
