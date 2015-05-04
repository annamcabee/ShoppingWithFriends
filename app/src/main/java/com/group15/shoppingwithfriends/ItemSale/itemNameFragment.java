package com.group15.shoppingwithfriends.ItemSale;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;


import com.group15.shoppingwithfriends.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class itemNameFragment extends DialogFragment {
    @Override
    /** pop up when item Name is not entered when registering interest
     * @param bundle of savedInstanceState
     * @return Dialog pop up box
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.item_name_fragment)
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