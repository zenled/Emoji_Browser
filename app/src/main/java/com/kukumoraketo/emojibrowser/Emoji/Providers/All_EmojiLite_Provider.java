package com.kukumoraketo.emojibrowser.Emoji.Providers;

import android.content.Context;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiCategory;
import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiLite;
import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiTone;
import com.kukumoraketo.emojibrowser.Emoji.EmojiDb.EmojiDb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zed on 29.4.2017.
 */

public class All_EmojiLite_Provider {

    private Context context;

    private List<EmojiLite> allEmoji;

    private EmojiDb emojiDb;


    public All_EmojiLite_Provider(Context context){
        this.context = context;

        this.emojiDb = new EmojiDb(context);

        this.allEmoji = this.emojiDb.getAllEmojiLite();
    }

    public List<EmojiLite> getEmoji(EmojiCategory category, EmojiTone tone){
        List<EmojiLite> r = new ArrayList<>();

        for (EmojiLite emoji : this.allEmoji){
            if (emoji.getCategory() == category && ( !emoji.hasTone() || emoji.getTone() == tone))
                r.add(emoji);
        }

        Collections.sort(r);

        return r;
    }
}
