package com.kukumoraketo.emojibrowser.Emoji.Emoji;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 29.4.2017.
 */

abstract class EmojiMinimal {

    protected String code;

    public EmojiMinimal(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public abstract int getDravableId(Context context);

}
