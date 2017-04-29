package com.kukumoraketo.emojibrowser.Emoji.Emoji;

/**
 * Created by zed on 29.4.2017.
 */

public enum EmojiTone {
    TONE_INVALID(-1),
    TONE_00 (0),
    TONE_01 (1),
    TONE_02 (2),
    TONE_03 (3),
    TONE_04 (4),
    TONE_05 (5);

    private final int value;

    EmojiTone(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EmojiTone getTone(int value){
        switch (value){
            case 0:
                return TONE_00;
            case 1:
                return TONE_01;
            case 2:
                return TONE_02;
            case 3:
                return TONE_03;
            case 4:
                return TONE_04;
            case 5:
                return TONE_05;
            default:
                return TONE_INVALID;
        }
    }
}
