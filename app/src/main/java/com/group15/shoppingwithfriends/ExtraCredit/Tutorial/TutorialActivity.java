package com.group15.shoppingwithfriends.ExtraCredit.Tutorial;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.group15.shoppingwithfriends.R;

/**
 * Created by annamcabee on 3/19/15.
 */
public class TutorialActivity extends ActionBarActivity {
    /**
     * on create, launch activity tutorial xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
    }

    /**
     * go to maps help fragment
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void goToMapsHelp(View view) {
        DialogFragment frag = new MapHelpFragment();

        frag.show(getFragmentManager(), "Maps Help");
    }

    /**
     * go to sales help fragments
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void goToSalesHelp(View view) {
        DialogFragment frag = new SaleHelpFragment();

        frag.show(getFragmentManager(), "Sales Help");
    }

    /**
     * go to items help fragment
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void goToItemsHelp(View view) {
        DialogFragment frag = new ItemHelpFragment();

        frag.show(getFragmentManager(), "Items Help");
    }

    /**
     * go to login help fragment
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void goToLoginHelp(View view) {
        DialogFragment frag = new LoginHelpFragment();

        frag.show(getFragmentManager(), "Login Help");
    }

    /**
     * go to friends help fragment
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void goToFriendsHelp(View view) {
        DialogFragment frag = new FriendsHelpFragment();

        frag.show(getFragmentManager(), "Friends Help");
    }
}