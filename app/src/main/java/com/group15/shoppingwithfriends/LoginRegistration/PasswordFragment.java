package com.group15.shoppingwithfriends.LoginRegistration;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import com.group15.shoppingwithfriends.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PasswordFragment extends DialogFragment {
    @Override
    /** pop up when password is not 5 characters
     * @param bundle of savedInstanceState
     * @return Dialog pop up box
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.password_fragment)
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