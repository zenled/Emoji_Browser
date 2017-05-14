package com.kukumoraketo.emojibrowser.EmojiDisplay;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiFull;
import com.kukumoraketo.emojibrowser.R;
import com.kukumoraketo.emojibrowser.Utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by zed on 7.5.2017.
 */

public class EmojiDetailsDialogFragment extends DialogFragment {

    private EmojiFull emoji;


    public EmojiDetailsDialogFragment() {
    }

    public static EmojiDetailsDialogFragment newInstance(EmojiFull emoji){
        EmojiDetailsDialogFragment dialogFragment = new EmojiDetailsDialogFragment();

        dialogFragment.setEmoji(emoji);

        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View mainView = View.inflate(getContext(), R.layout.dialog_emoji_details, null);
        builder.setView(mainView);

        fillDialog(mainView);

        builder.setPositiveButton(R.string.emoji_details_dialog_fragment_positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        return builder.create();
    }

    public void setEmoji(EmojiFull emoji){
        this.emoji  = emoji;
    }

    private void fillDialog(View mainView){

        //region sets Texts and Image
        ImageView imageView = (ImageView) mainView.findViewById(R.id.emoji_imageView);
        Picasso.with(getContext()).load(this.emoji.getDravableId(getContext())).into(imageView);

        TextView nameView = (TextView) mainView.findViewById(R.id.emoji_name_textView);
        nameView.setText(StringUtils.toCamelCase(this.emoji.getName()));

        TextView unicodeView = (TextView) mainView.findViewById(R.id.emoji_unicode_textView);
        unicodeView.setText(this.emoji.getCode());

        TextView shortNameView = (TextView) mainView.findViewById(R.id.emoji_shortName_textView);
        shortNameView.setText(this.emoji.getShortName());

        TextView hasTone = (TextView) mainView.findViewById(R.id.emoji_hastone_textView);
        if (this.emoji.hasTone()) {
            hasTone.setText(R.string.emoji_has_tone_true);
        } else {
            hasTone.setText(R.string.emoji_has_tone_false);
        }

        TextView categoryView = (TextView) mainView.findViewById(R.id.emoji_category_textView);
        categoryView.setText(StringUtils.toCamelCase(this.emoji.getCategory().toString()));

        TextView keywordsView = (TextView) mainView.findViewById(R.id.emoji_keywords_textView);
        StringBuilder keywordsStringBuilder = new StringBuilder();
        List<String> keywords = emoji.getKeywords();
        Collections.sort(keywords);
        for (int i = 0; i < keywords.size(); i++) {
            keywordsStringBuilder.append(keywords.get(i));
            if (i != (keywords.size() - 1)) {
                keywordsStringBuilder.append(", ");
            }
        }
        keywordsView.setText(keywordsStringBuilder.toString());
        //endregion

        //region setsCopyButton
        Button copyButton = (Button) mainView.findViewById(R.id.emoji_copy_button);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("emoji", emoji.getUnicodeProper());
                clipboard.setPrimaryClip(clipData);

                Snackbar snackbar = Snackbar.make(v,"Copied to ClipBoard", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
        //endregion

    }

}
