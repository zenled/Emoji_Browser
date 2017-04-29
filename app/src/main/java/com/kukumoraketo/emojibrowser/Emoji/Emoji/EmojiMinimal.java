package com.kukumoraketo.emojibrowser.Emoji.Emoji;

import android.content.Context;

/**
 * Created by zed on 29.4.2017.
 */

abstract class EmojiMinimal {

    protected String unicode;

    public EmojiMinimal(String unicode){
        this.unicode = unicode;
    }

    public String getUnicode(){
        return this.unicode;
    }

    public abstract int getDravableId(Context context);

}
