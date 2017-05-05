package com.kukumoraketo.emojibrowser.EmojiDisplay;

import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.kukumoraketo.emojibrowser.R;

/**
 * Created by zed on 2.5.2017.
 */

public class VisualMaster {

    private VisualMaster(){}

    /**
     * Changes emoji_display_single so that it indicates if emoji is selected or not
     * @param view view emoji_display_single
     * @param isSelected true if emoji is selected
     */
    public static void updateSelectionIndicator(Resources resources, View view, boolean isSelected){

        // sets background depending if it is selected
        if (isSelected){
            int color =  ResourcesCompat.getColor(resources,R.color.color_emojiSelected_true, null);
            view.setBackgroundColor(color);
        }
        else{
            int color =  ResourcesCompat.getColor(resources, R.color.color_emojiSelected_false, null);
            view.setBackgroundColor(color);
        }
    }

}
