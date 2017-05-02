package com.kukumoraketo.emojibrowser.Emoji.Emoji;

import com.kukumoraketo.emojibrowser.R;

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

    public static int getIcon(EmojiTone tone){
        switch (tone){
            case TONE_00:
                return R.drawable.ic_tone00;
            case TONE_01:
                return R.drawable.ic_tone01;
            case TONE_02:
                return R.drawable.ic_tone02;
            case TONE_03:
                return R.drawable.ic_tone03;
            case TONE_04:
                return R.drawable.ic_tone04;
            case TONE_05:
                return R.drawable.ic_tone05;
            default:
                return R.drawable.em_2049;
        }
    }
}
