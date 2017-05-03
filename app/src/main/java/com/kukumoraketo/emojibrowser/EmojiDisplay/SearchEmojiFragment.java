package com.kukumoraketo.emojibrowser.EmojiDisplay;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridView;

import com.kukumoraketo.emojibrowser.IActivitiContainingSearchEmojiFragment;
import com.kukumoraketo.emojibrowser.R;

import java.util.ArrayList;

/**
 * Created by zed on 3.5.2017.
 */

public class SearchEmojiFragment extends BasicEmojiDisplayFragment {

    EditText searchField;

    IActivitiContainingSearchEmojiFragment parentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_emoji, container, false);

        // gets parent Activity
        //TODO parentActivity = (IActivitiContainingSearchEmojiFragment) getActivity();

        // sets GridView
        this.gridView = (GridView) v.findViewById(R.id.emojiDisplay_GridView);

        if (fragmentType != null){
            this.forceEmojiRefresh();
            this.forceGridViewRefresh();
        }

        //region sets searchFiels (EditText)
        this.searchField = (EditText) v.findViewById(R.id.search_field);

        this.searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (keyCode == EditorInfo.IME_ACTION_DONE)) {
                    //TODO parentActivity.hideKeyboard(v);
                }
                return false;
            }
        });

        this.searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                forceEmojiRefresh();
                forceGridViewRefresh();
            }
        });
        //endregion


        return v;
    }

    @Override
    protected void autoInflate() {
        String searchString = getSearchString();

        if (searchString != null){
            this.myEmoji = this.provider.getEmoji(searchString);
        }
        else{
            this.myEmoji = new ArrayList<>();
        }
    }

    private String getSearchString(){

        if (this.searchField != null) {
            String searchString = searchField.getText().toString().trim().toLowerCase();

            if (searchString.equals("")){
                return null;
            }

            return searchField.getText().toString();
        }
        else{
            return null;
        }

    }



}
