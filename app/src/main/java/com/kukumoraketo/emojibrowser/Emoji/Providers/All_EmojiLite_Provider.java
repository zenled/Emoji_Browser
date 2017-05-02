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

public class All_EmojiLite_Provider implements EmojiProvider{

    private Context context;

    private EmojiDb emojiDb;

    private List<EmojiLite> allEmoji;
    private EmojiTone tone; // selected EmojiTone


    public All_EmojiLite_Provider(Context context, EmojiTone emojiTone){
        this.context = context;

        this.emojiDb = new EmojiDb(context);

        this.allEmoji = this.emojiDb.getAllEmojiLite();
        this.tone = emojiTone;
    }


    @Override
    public List<EmojiLite> getEmoji(EmojiCategory category){
        List<EmojiLite> r = new ArrayList<>();

        for (EmojiLite emoji : this.allEmoji){
            if (emoji.getCategory() == category && ( !emoji.hasTone() || emoji.getTone() == this.tone))
                r.add(emoji);
        }

        Collections.sort(r);

        return r;
    }

    @Override
    public EmojiTone getTone() {
        return this.tone;
    }

    @Override
    public void setTone(EmojiTone newTone) {
        this.tone = newTone;
    }
}
