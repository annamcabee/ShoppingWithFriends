package com.group15.shoppingwithfriends.LoginRegistration;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.MainWelcome.MainActivity;
import com.group15.shoppingwithfriends.MainWelcome.WelcomeScreen;
import com.group15.shoppingwithfriends.R;


public class RegistrationActivity extends Activity {

    private DataSource datasource;

    /**
     * on create for registration activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //database setup
        datasource = new DataSource(this);
        datasource.open();
    }


    @Override
    /**
     * adds item to the action bar if it is present
     * @param menu
     * @retunr boolean on if inflated
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    /**
     *  Handle action bar item clicks here.
     *  @param MenuItem
     *  @return a boolean of ig selected
     *
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This checks to see if the entered registration info is allowed, valid, and available
     * @param view view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void checkRegistration(View view) {

        EditText usernameText = (EditText) findViewById(R.id.reg_ID);
        String username = usernameText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.reg_password);
        String password = passwordText.getText().toString();

        EditText nameText = (EditText) findViewById(R.id.reg_name);
        String name = nameText.getText().toString();

        EditText phoneText = (EditText) findViewById(R.id.phoneNumber);
        String phone = phoneText.getText().toString();

        EditText emailText = (EditText) findViewById(R.id.reg_email);
        String email = emailText.getText().toString();

        EditText motherText = (EditText) findViewById(R.id.reg_sec2);
        String mother = motherText.getText().toString();

        EditText streetText = (EditText) findViewById(R.id.reg_sec1);
        String street = streetText.getText().toString();


        // check to see if the username is already taken

        if (datasource.userExists(username) || !User.isUsernameValid(username)) {
            DialogFragment newFragment = new USInvalidFragment();
            newFragment.show(getFragmentManager(), "Username taken/invalid");

            System.out.println("Username already exists");
            return;
        }

        //check to see if the password is valid
        if (!User.isPasswordValid(password)) {
            DialogFragment newFragment = new PasswordFragment();
            newFragment.show(getFragmentManager(), "Password invalid");
            System.out.println("Invalid password");
            return;
        }
        //make sure that the phone number has 10 digits
        if (!User.isPhoneNumberValid(phone)) {
            DialogFragment newFragment = new PhoneFragment();
            newFragment.show(getFragmentManager(), "Phone number invalid");
            System.out.println("Invalid phone number");
            return;
        }
        //make sure that the email is valid (i.e. make sure there is an @ symbol)
        if (!User.isEmailValid(email)) {
            DialogFragment newFragment = new EmailFragment();
            newFragment.show(getFragmentManager(), "Email invalid");
            System.out.println("Invalid email");
            return;
        }

        User newUser = datasource.createUser(username, password, name, phone, email, mother, street);
        User.setCurrentUser(newUser);
        Intent intent  = new Intent(this, MainActivity.class);
        startActivity(intent);
        System.out.println("Success");
    }

    @Override
    /**
     * handlese resuming datasource
     */
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    /**
     * handles pausing data source
     */
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    /**
     * cancels registration
     * @param view
     */
    public void cancelRegistration(View view) {
        Intent intent  = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }


}
