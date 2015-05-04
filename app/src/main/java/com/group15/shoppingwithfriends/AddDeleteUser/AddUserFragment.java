package com.group15.shoppingwithfriends.AddDeleteUser;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;


import com.group15.shoppingwithfriends.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AddUserFragment extends DialogFragment {
    @Override
    /**
     * @param Bundle the instance state you are in
     * @returns Dialog pop up box if user has been added
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.add_user_string)
                .setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}