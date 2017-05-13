package com.kukumoraketo.emojibrowser;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

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

        //region sets text
        TextView aboutContent = (TextView) mainView.findViewById(R.id.about_content_TextView);
        aboutContent.setText(Html.fromHtml(getString(R.string.about_content)));
        aboutContent.setMovementMethod(LinkMovementMethod.getInstance());

        TextView licenseContent = (TextView) mainView.findViewById(R.id.about_license_content_TextView);
        licenseContent.setText(Html.fromHtml(getString(R.string.about_license_content)));
        licenseContent.setMovementMethod(LinkMovementMethod.getInstance());
        //endregion

        return builder.create();
    }
}
