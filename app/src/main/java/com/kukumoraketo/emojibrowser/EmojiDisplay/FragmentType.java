package com.kukumoraketo.emojibrowser.EmojiDisplay;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiCategory;

/**
 * Created by zed on 1.5.2017.
 */

public enum FragmentType {

    EMOJI_SEARCH,
    PEOPLE,
    NATURE,
    ACTIVITY,
    FLAGS,
    OBJECTS,
    SYMBOLS,
    REGIONAL,
    MODIFIER,
    TRAVEL,
    FOOD,
    NOT_VALID;

    public static EmojiCategory toEmojiCategory(FragmentType fragment_category){

        switch (fragment_category){
            case PEOPLE:
                return EmojiCategory.PEOPLE;
            case NATURE:
                return EmojiCategory.NATURE;
            case ACTIVITY:
                return EmojiCategory.ACTIVITY;
            case FLAGS:
                return EmojiCategory.FLAGS;
            case OBJECTS:
                return EmojiCategory.OBJECTS;
            case SYMBOLS:
                return EmojiCategory.SYMBOLS;
            case REGIONAL:
                return EmojiCategory.REGIONAL;
            case MODIFIER:
                return EmojiCategory.MODIFIER;
            case TRAVEL:
                return EmojiCategory.TRAVEL;
            case FOOD:
                return EmojiCategory.FOOD;

            default:
                return EmojiCategory.NOT_VALID;
        }

    }

}
