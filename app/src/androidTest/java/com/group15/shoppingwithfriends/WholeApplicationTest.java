package com.group15.shoppingwithfriends;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.group15.shoppingwithfriends.FriendsProfileList.FriendsListMain;
import com.group15.shoppingwithfriends.MainWelcome.MainActivity;

import org.junit.Test;

/**
 * Created by annamcabee on 4/7/15.
 */
public class WholeApplicationTest extends android.test.ActivityUnitTestCase<MainActivity> {
    public WholeApplicationTest() {
        super(MainActivity.class);
    }

    private MainActivity activity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getInstrumentation().getTargetContext(),
                MainActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }
    /**
     *
     */
    @Test
    public void testFriends() {

        Button btnLaunch = (Button) activity.findViewById(R.id.button_friends);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);
        assertEquals(FriendsListMain.class, triggeredIntent.getClass());
    }
    /**
     *
     */
    @Test
    public void testWishList() {

    }
    /**
     *
     */
    @Test
    public void testSalesReport() {
        EditText username = (EditText) activity.findViewById(R.id.reg_ID);
        username.setText("anna13");

        EditText name = (EditText) activity.findViewById(R.id.reg_name);
        name.setText("Anna");

        EditText email = (EditText) activity.findViewById(R.id.reg_email);
        email.setText("annamcabee@gmail.com");

        EditText pass = (EditText) activity.findViewById(R.id.reg_password);
        pass.setText("12345");

        EditText phone = (EditText) activity.findViewById(R.id.phoneNumber);
        phone.setText("4042761846");

        EditText street = (EditText) activity.findViewById(R.id.reg_sec2);
        street.setText("Meeting");

        EditText mothersMaiden = (EditText) activity.findViewById(R.id.reg_sec1);
        mothersMaiden.setText("Gustavson");


        Button btnLaunch = (Button) activity.findViewById(R.id.reg_button2);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);
    }
    /**
     *
     */
    @Test
    public void testMapView() {
        EditText username = (EditText) activity.findViewById(R.id.reg_ID);
        username.setText("anna13");

        EditText name = (EditText) activity.findViewById(R.id.reg_name);
        name.setText("Anna");

        EditText email = (EditText) activity.findViewById(R.id.reg_email);
        email.setText("annamcabee@gmail.com");

        EditText pass = (EditText) activity.findViewById(R.id.reg_password);
        pass.setText("12345");

        EditText phone = (EditText) activity.findViewById(R.id.phoneNumber);
        phone.setText("4042761846");

        EditText street = (EditText) activity.findViewById(R.id.reg_sec2);
        street.setText("Meeting");

        EditText mothersMaiden = (EditText) activity.findViewById(R.id.reg_sec1);
        mothersMaiden.setText("Gustavson");


        Button btnLaunch = (Button) activity.findViewById(R.id.reg_button2);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);
    }

}
