package com.group15.shoppingwithfriends.ExtraCredit.Tutorial;

import android.app.DialogFragment;
import android.view.View;

/**
 * Created by annamcabee on 4/22/15.
 */
public class LoginHelpCommand extends AbstractCommand {
    @Override
    public void execute(View view) {
        DialogFragment frag = new LoginHelpFragment();
        //frag.show(getP, "Login Help");
    }
}
