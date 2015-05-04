package com.group15.shoppingwithfriends;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.group15.shoppingwithfriends.LoginRegistration.RegistrationActivity;

import org.junit.Test;


/**
 * Created by annamcabee on 3/23/15.
 */
public class ShoppingWithFriendsTest extends
        android.test.ActivityUnitTestCase<RegistrationActivity>{

    public ShoppingWithFriendsTest() {
        super(RegistrationActivity.class);
    }

    private RegistrationActivity activity;



    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getInstrumentation().getTargetContext(),
                RegistrationActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    /**
     * Anna McAbee's test of Method checkRegistration in Registration Activity
     *
     */
    @Test
    public void testRegistrationValidInfo() {
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
     * Anna McAbee's test of Method checkRegistration in Registration Activity
     *
     */
    @Test
    public void testRegistrationInvalidPass() {
        EditText username = (EditText) activity.findViewById(R.id.reg_ID);
        username.setText("anna1");

        EditText name = (EditText) activity.findViewById(R.id.reg_name);
        name.setText("Anna");

        EditText email = (EditText) activity.findViewById(R.id.reg_email);
        email.setText("annamcabee@gmail.com");

        EditText pass = (EditText) activity.findViewById(R.id.reg_password);
        pass.setText("125");

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
        assertNull("Intent should have triggered after button press",
                triggeredIntent);
    }
    /**
     * Anna McAbee's test of Method checkRegistration in Registration Activity
     *
     */
    @Test
    public void testRegistrationInvalidPhone() {
        EditText username = (EditText) activity.findViewById(R.id.reg_ID);
        username.setText("anna1");

        EditText name = (EditText) activity.findViewById(R.id.reg_name);
        name.setText("Anna");

        EditText email = (EditText) activity.findViewById(R.id.reg_email);
        email.setText("annamcabee@gmail.com");

        EditText pass = (EditText) activity.findViewById(R.id.reg_password);
        pass.setText("12345");

        EditText phone = (EditText) activity.findViewById(R.id.phoneNumber);
        phone.setText("4042746");

        EditText street = (EditText) activity.findViewById(R.id.reg_sec2);
        street.setText("Meeting");

        EditText mothersMaiden = (EditText) activity.findViewById(R.id.reg_sec1);
        mothersMaiden.setText("Gustavson");


        Button btnLaunch = (Button) activity.findViewById(R.id.reg_button2);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should have triggered after button press",
                triggeredIntent);
    }
    /**
     * Anna McAbee's test of Method checkRegistration in Registration Activity
     *
     */
    @Test
    public void testRegistrationInvalidEmail() {
        EditText username = (EditText) activity.findViewById(R.id.reg_ID);
        username.setText("anna1");

        EditText name = (EditText) activity.findViewById(R.id.reg_name);
        name.setText("Anna");

        EditText email = (EditText) activity.findViewById(R.id.reg_email);
        email.setText("annamcabeegmail.com");

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
        assertNull("Intent should have triggered after button press",
                triggeredIntent);
    }
    /**
     * Anna McAbee's test of Method checkRegistration in Registration Activity
     *
     */
    @Test
    public void testRegistrationInvalidUser() {
        EditText username = (EditText) activity.findViewById(R.id.reg_ID);
        username.setText("anna11");

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
        assertNull("Intent should have triggered after button press",
                triggeredIntent);
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
