package com.kukumoraketo.emojibrowser.EmojiDisplay;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiLite;
import com.kukumoraketo.emojibrowser.Emoji.Providers.EmojiProvider;

import java.util.List;

/**
 * Created by zed on 1.5.2017.
 */

public interface EmojiDisplayFragment {

    /**
     * Sets EmojiProvider (class that gives apropriate emoji to fragment)
     * @param emojiProvider
     */
    void setEmojiProvider(EmojiProvider emojiProvider);

    /**
     * Which category of emojis will be displayed in this fragment
     * (This is used when emoji autoInflate)
     * @param fragmentType category of fragment
     */
    void setFragmentType(FragmentType fragmentType);

    /**
     * Returns category of this fragment
     * @return category of fragment
     */
    FragmentType getFragmentType();

    /**
     * Forces the fragment to refresh the gridView that contains emoji
     * doesn't get new emoji
     */
    void forceGridViewRefresh();

    /**
     * Returns All emoji Displayed in this fragment
     * @return emoji displayed in this fragment
     */
    List<EmojiLite> getEmoji();

    /**
     * Forces fragment to ask activity for emoji
     * this methid doesn't refresh the gridView
     */
    void forceEmojiRefresh();

}
