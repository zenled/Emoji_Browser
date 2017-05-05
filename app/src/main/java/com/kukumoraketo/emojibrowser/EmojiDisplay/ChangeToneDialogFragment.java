package com.kukumoraketo.emojibrowser.EmojiDisplay;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kukumoraketo.emojibrowser.Emoji.Emoji.EmojiTone;
import com.kukumoraketo.emojibrowser.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zed on 2.5.2017.
 */

public class ChangeToneDialogFragment extends DialogFragment {

    private EmojiTone selectedTone;

    private List<LinearLayout> selections = new ArrayList<>();

    private ChangeToneListener changeToneListener;

    public interface ChangeToneListener{
        void onChangeToneDialogPositiveClick(EmojiTone selectedTone);
    }

    public static ChangeToneDialogFragment newInstance(EmojiTone selectedTone) {
        ChangeToneDialogFragment dialog = new ChangeToneDialogFragment();
        Bundle args = new Bundle();
        args.putInt("selected_tone", selectedTone.getValue());
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        this.selectedTone = EmojiTone.getTone(getArguments().getInt("selected_tone"));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View mainView = View.inflate(getContext(), R.layout.dialog_change_tone, null);
        builder.setView(mainView);

        //region fills dialog fragment
        LinearLayout row1 = (LinearLayout) mainView.findViewById(R.id.dialog_change_tone_row1);
        LinearLayout row2 = (LinearLayout) mainView.findViewById(R.id.dialog_change_tone_row2);

        fillDialog(row1, row2);
        updateSelectedTone();
        //endregion

        Bundle args = getArguments();

        this.selectedTone = EmojiTone.getTone(args.getInt("selected_tone"));


        builder.setPositiveButton(R.string.dialog_change_tone_positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeToneListener.onChangeToneDialogPositiveClick(selectedTone);
            }
        });

        builder.setNegativeButton(R.string.dialog_change_tone_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    private void updateSelectedTone(){
        // Goes through all layouts and sets their visual selection indicator
        for (int i = 0; i < this.selections.size(); i++){
            LinearLayout toneLayout = selections.get(i);
            if (i == this.selectedTone.getValue()){
                VisualMaster.updateSelectionIndicator(getResources(), toneLayout, true);
            }
            else{
                VisualMaster.updateSelectionIndicator(getResources(), toneLayout, false);
            }
        }
    }

    private void fillDialog(LinearLayout row1, LinearLayout row2){

        View toneView;
        ImageView image;
        //fills row1
        toneView = LayoutInflater.from(getContext()).inflate(R.layout.emoji_display_single,null);
        image =  (ImageView) toneView.findViewById(R.id.emojiDisplaySingle_ImageView);
        image.setImageResource(R.drawable.ic_tone00);
        selections.add((LinearLayout) toneView);
        row1.addView(toneView);

        toneView = LayoutInflater.from(getContext()).inflate(R.layout.emoji_display_single,null);
        image =  (ImageView) toneView.findViewById(R.id.emojiDisplaySingle_ImageView);
        image.setImageResource(R.drawable.ic_tone01);
        selections.add((LinearLayout) toneView);
        row1.addView(toneView);

        toneView = LayoutInflater.from(getContext()).inflate(R.layout.emoji_display_single,null);
        image =  (ImageView) toneView.findViewById(R.id.emojiDisplaySingle_ImageView);
        image.setImageResource(R.drawable.ic_tone02);
        selections.add((LinearLayout) toneView);
        row1.addView(toneView);

        //fils row2
        toneView = LayoutInflater.from(getContext()).inflate(R.layout.emoji_display_single,null);
        image =  (ImageView) toneView.findViewById(R.id.emojiDisplaySingle_ImageView);
        image.setImageResource(R.drawable.ic_tone03);
        selections.add((LinearLayout) toneView);
        row2.addView(toneView);

        toneView = LayoutInflater.from(getContext()).inflate(R.layout.emoji_display_single,null);
        image =  (ImageView) toneView.findViewById(R.id.emojiDisplaySingle_ImageView);
        image.setImageResource(R.drawable.ic_tone04);
        selections.add((LinearLayout) toneView);
        row2.addView(toneView);

        toneView = LayoutInflater.from(getContext()).inflate(R.layout.emoji_display_single,null);
        image =  (ImageView) toneView.findViewById(R.id.emojiDisplaySingle_ImageView);
        image.setImageResource(R.drawable.ic_tone05);
        selections.add((LinearLayout) toneView);
        row2.addView(toneView);


        // sets onClickListener-s
        for (int i = 0; i < selections.size(); i++){
            LinearLayout view = selections.get(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tone = selections.indexOf(v);
                    selectedTone = EmojiTone.getTone(tone);
                    updateSelectedTone();
                }
            });
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            this.changeToneListener = (ChangeToneListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement ChangeToneListener");
        }

    }

}
