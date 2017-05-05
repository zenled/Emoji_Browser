package com.kukumoraketo.emojibrowser.EmojiDisplay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.kukumoraketo.emojibrowser.Emoji.Providers.EmojiProvider;
import com.kukumoraketo.emojibrowser.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 1.5.2017.
 */

public class EmojiDisplayPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    public List<EmojiDisplayFragment> fragments = new ArrayList<>();
    private EmojiProvider emojiProvider;

    private int[] tabLayoutIcons = {
            R.drawable.ic_search,
            R.drawable.ic_people,
            R.drawable.ic_nature,
            R.drawable.ic_food,
            R.drawable.ic_activity,
            R.drawable.ic_travel,
            R.drawable.ic_objects,
            R.drawable.ic_symbols,
            R.drawable.ic_flags,
    };

    public EmojiDisplayPagerAdapter(FragmentManager fm, EmojiProvider emojiProvider,Context context) {
        super(fm);

        this.context = context;

        this.emojiProvider = emojiProvider;

        for (int i = 0; i < getCount(); i++){
            this.fragments.add(null);
        }
    }

    @Override
    public Fragment getItem(int position) {
        EmojiDisplayFragment fragment;

        if (position == 0){
            fragment = new SearchEmojiFragment();
        }
        else {
            fragment = new BasicEmojiDisplayFragment();
        }

        FragmentType typeOfFragment;

        switch (position){
            case 0: // position 0 is search
                typeOfFragment = FragmentType.EMOJI_SEARCH;
                break;
            case 1:
                typeOfFragment = FragmentType.PEOPLE;
                break;
            case 2:
                typeOfFragment = FragmentType.NATURE;
                break;
            case 3:
                typeOfFragment = FragmentType.FOOD;
                break;
            case 4:
                typeOfFragment = FragmentType.ACTIVITY;
                break;
            case 5:
                typeOfFragment = FragmentType.TRAVEL;
                break;
            case 6:
                typeOfFragment = FragmentType.OBJECTS;
                break;
            case 7:
                typeOfFragment = FragmentType.SYMBOLS;
                break;
            case 8:
                typeOfFragment = FragmentType.FLAGS;
                break;
            default:
                typeOfFragment = FragmentType.NOT_VALID;
        }

        fragment.setFragmentType(typeOfFragment);
        fragment.setEmojiProvider(this.emojiProvider);

        this.fragments.set(position, fragment);

        return (Fragment) fragment;
    }

    @Override
    public int getCount() {
        return tabLayoutIcons.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = ContextCompat.getDrawable(context, tabLayoutIcons[position]);
        image.setBounds(0, 0, 80, 80);
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
