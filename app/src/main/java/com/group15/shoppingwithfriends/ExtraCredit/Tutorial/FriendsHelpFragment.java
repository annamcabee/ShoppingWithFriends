package com.group15.shoppingwithfriends.ExtraCredit.Tutorial;

/**
 * Created by annamcabee on 3/19/15.
 */

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import com.group15.shoppingwithfriends.R;

/**
 * Created by annamcabee on 3/19/15.
 */


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FriendsHelpFragment extends DialogFragment  {

    @Override
    /**
     * @param Bundle the instance state you are in
     * @returns Dialog pop up box if user has been added
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.friend_help_string)
                .setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        getDialog().dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    public void execute() {

    }
}