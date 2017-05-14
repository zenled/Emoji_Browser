package com.kukumoraketo.emojibrowser.Emoji.Emoji;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 7.5.2017.
 */

public class EmojiFull extends EmojiLite {

    private String codePoint;
    private String name;
    private String shortName;
    private List<String> keywords;

    public EmojiFull(String code, String codePoint, boolean hasTone, EmojiTone tone, int emojiOrder, EmojiCategory category, String name, String shortName, List<String> keywords){
        super(code, hasTone, tone, emojiOrder, category);

        this.codePoint = codePoint;
        this.name = name;
        this.shortName = shortName;
        this.keywords = keywords;
    }

    public EmojiFull(String code, String codePoint, boolean hasTone, int tone, int emojiOrder, EmojiCategory category, String name, String shortName, List<String> keywords){
        super(code, hasTone, tone, emojiOrder, category);

        this.codePoint = codePoint;
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

    /**
     * Returns proper unicode representation of emoji
     * @return unicode representation
     */
    public String getUnicodeProper(){
        List<Integer> parts = new ArrayList<>();

        for (String s : this.codePoint.split("-")){
            parts.add(Integer.parseInt(s, 16));
        }

        StringBuilder sb = new StringBuilder();
        for(int i : parts){
            sb.append(Character.toChars(i));
        }

        return sb.toString();
    }
}
