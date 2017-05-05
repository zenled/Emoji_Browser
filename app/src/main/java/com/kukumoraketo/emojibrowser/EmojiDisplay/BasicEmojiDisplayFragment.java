package com.kukumoraketo.emojibrowser.EmojiDisplay;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiLite;
import com.kukumoraketo.emojibrowser.Emoji.Providers.EmojiProvider;
import com.kukumoraketo.emojibrowser.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicEmojiDisplayFragment extends Fragment implements EmojiDisplayFragment{

    protected GridView gridView;

    protected FragmentType fragmentType;

    protected EmojiProvider provider;
    protected List<EmojiLite> myEmoji;


    public BasicEmojiDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_emoji_display, container, false);

        this.gridView = (GridView) v.findViewById(R.id.emojiDisplay_GridView);

        if (fragmentType != null){
            this.forceEmojiRefresh();
            this.forceGridViewRefresh();
        }

        return v;
    }

    @Override
    public void setFragmentType(FragmentType fragmentType) {
        this.fragmentType = fragmentType;
    }

    @Override
    public FragmentType getFragmentType() {
        return this.fragmentType;
    }

    @Override
    public void forceGridViewRefresh() {

        Parcelable state = this.gridView.onSaveInstanceState(); // (gets scroll)

        refreshEmojisGridView();

        this.gridView.onRestoreInstanceState(state); // (sets scroll)

    }

    @Override
    public List<EmojiLite> getEmoji() {
        return this.myEmoji;
    }

    @Override
    public void forceEmojiRefresh() {
        autoInflate();
    }

    @Override
    public void setEmojiProvider(EmojiProvider emojiProvider) {
        this.provider = emojiProvider;
    }

    protected void autoInflate(){

        if (this.fragmentType == FragmentType.EMOJI_SEARCH)
            // TODO throw error this fragment cannot be of type EMOJI_SEARCH
            this.myEmoji = new ArrayList<>();
        else
            this.myEmoji = this.provider.getEmoji(FragmentType.toEmojiCategory(this.fragmentType));
    }

    protected void refreshEmojisGridView(){

        final EmojiDisplayAdapter adapter = new EmojiDisplayAdapter(myEmoji, getContext());

        this.gridView.setAdapter(adapter);
    }
}
