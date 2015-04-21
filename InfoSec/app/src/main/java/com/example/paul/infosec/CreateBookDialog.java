package com.example.paul.infosec;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by paul on 11/13/14.
 */
public class CreateBookDialog extends DialogFragment {
    public static final String EXTRA_TITLE = "com.chris.android.comicalstudios.title";
    public static final String EXTRA_USERNAME = "com.chris.android.comicalstudios.username";
    public static final String EXTRA_PASSWORD = "com.chris.android.comicalstudios.password";

    private String title;
    private String username;
    private String password;

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null)
            return;

        Intent i = new Intent();
        i.putExtra(EXTRA_TITLE, title);
        i.putExtra(EXTRA_USERNAME,username);
        i.putExtra(EXTRA_PASSWORD, password);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_create_book, null);

      //  final EditText editText = (EditText)v.findViewById(R.id.book_title);

        //inflater.inflate(R.layout.dialog_create_book, null)
        builder.setView(v)
                .setPositiveButton(
                        android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Dialog f = (Dialog) dialog;
                                EditText editText = (EditText)f.findViewById(R.id.book_title);
                                title = editText.getText().toString();

                                EditText editText1 = (EditText)f.findViewById(R.id.book_username);
                                username = editText1.getText().toString();

                                EditText editText2 = (EditText)f.findViewById(R.id.book_password);
                                password = editText2.getText().toString();

                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        return builder.create();
    }

}
