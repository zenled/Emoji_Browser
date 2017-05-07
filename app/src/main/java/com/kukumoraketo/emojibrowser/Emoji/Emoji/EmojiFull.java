package com.kukumoraketo.emojibrowser.Emoji.Emoji;

import java.util.List;

/**
 * Created by zed on 7.5.2017.
 */

public class EmojiFull extends EmojiLite {

    private String name;
    private String shortName;
    private List<String> keywords;

    public EmojiFull(String unicode, boolean hasTone, EmojiTone tone, int emojiOrder, EmojiCategory category, String name, String shortName, List<String> keywords){
        super(unicode, hasTone, tone, emojiOrder, category);

        this.name = name;
        this.shortName = shortName;
        this.keywords = keywords;
    }

    public EmojiFull(String unicode, boolean hasTone, int tone, int emojiOrder, EmojiCategory category, String name, String shortName, List<String> keywords){
        super(unicode, hasTone, tone, emojiOrder, category);

        this.name = name;
        this.shortName = shortName;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
