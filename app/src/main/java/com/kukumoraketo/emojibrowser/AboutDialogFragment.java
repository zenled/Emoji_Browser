package com.kukumoraketo.emojibrowser;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by zed on 9.5.2017.
 */

public class AboutDialogFragment extends DialogFragment {

    public static AboutDialogFragment newInstance(){
        AboutDialogFragment dialogFragment = new AboutDialogFragment();
        Bundle args = new Bundle();
        dialogFragment.setArguments(args);
        return dialogFragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View mainView = View.inflate(getContext(), R.layout.dialog_about, null);
        builder.setView(mainView);

        builder.setPositiveButton(R.string.about_positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
