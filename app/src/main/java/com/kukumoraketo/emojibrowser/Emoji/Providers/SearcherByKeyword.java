package com.kukumoraketo.emojibrowser.Emoji.Providers;

import com.kukumoraketo.emojibrowser.Emoji.EmojiDb.EmojiDb;

import java.util.List;

/**
 * Created by zed on 3.5.2017.
 */

class SearcherByKeyword {

    // private constructor: this is a static only method
    private SearcherByKeyword(){
    }

    public static List<String> getUnicodeByKeywords(EmojiDb database, String keywordsString){
        String[] keyWords = keywordsString.split(" +");

        return database.getUnicodeByKeyword(keyWords);
    }


}
