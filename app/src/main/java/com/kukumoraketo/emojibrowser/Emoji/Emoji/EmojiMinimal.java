package com.kukumoraketo.emojibrowser.Emoji.Emoji;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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

    public String getUnicodeProper() {

        List<Integer> parts = new ArrayList<>();

        for (String s : this.unicode.split("-")){
            parts.add(Integer.parseInt(s, 16));
        }

        StringBuilder sb = new StringBuilder();
        for(int i : parts){
            sb.append(Character.toChars(i));
        }

        return sb.toString();
    }

}
