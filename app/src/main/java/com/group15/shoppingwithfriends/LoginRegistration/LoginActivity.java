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



public class LoginActivity extends Activity {

    private DataSource datasource;
    private static int numIncorrectAttempts = 0;



    @Override
    /**
     * @params Bundle your saved instance state
     * This method executes when activity is started
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        datasource = new DataSource(this);
        datasource.open();

    }


    @Override
    /**
     * adds items to action bar if present
     * @params menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    /**
     * @params item
     *  Handle action bar item clicks here. The action bar will
     * automatically handle clicks on the Home/Up button, so long
     *  as you specify a parent activity in AndroidManifest.xml.
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
     * attempts login
     *
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void tryLogin(View view) {
        EditText usernameText = (EditText) findViewById(R.id.edit_user);
        EditText passwordText = (EditText) findViewById(R.id.edit_password);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String actualPassword = datasource.getPassword(username);
        if (actualPassword == null) {
            DialogFragment newFragment = new LoginFailedFragment();
            numIncorrectAttempts++;
            newFragment.show(getFragmentManager(), "Username or Password Incorrect");
            System.out.println("Username does not exist");
            if (numIncorrectAttempts == 3) {
                System.out.println("3 incorrect login attempts -- locked out.");
                Intent intent = new Intent(this, LockoutActivity.class);
                startActivity(intent);
            }
            return;
        }
        if (!actualPassword.equals(password)) {
            DialogFragment newFragment = new LoginFailedFragment();
            numIncorrectAttempts++;
            newFragment.show(getFragmentManager(), "Username or Password Incorrect");
            System.out.println("Incorrect password");
            if (numIncorrectAttempts == 3) {
                System.out.println("3 incorrect login attempts -- locked out.");
                Intent intent = new Intent(this, LockoutActivity.class);
                startActivity(intent);
            }
            return;
        }
        if (numIncorrectAttempts == 3) {
                System.out.println("3 incorrect login attempts -- locked out.");
                Intent intent = new Intent(this, LockoutActivity.class);
                startActivity(intent);
        } else {
            System.out.println("Logged in");
            User current = datasource.getUser(username);
            User.setCurrentUser(datasource.getUser(username));
         //   SceneTransitionsActivity sceneTransitionsActivity = new SceneTransitionsActivity();
       //     sceneTransitionsActivity.goToScene2(view);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    /**
     * cancels login
     *
     * @param view
     */
    public void cancelLogin(View view) {
        //setContentView(R.layout.activity_welcome_screen);
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassActivity.class);
        startActivity(intent);
    }

    /**
     * resume login
     */
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    /**
     * pause login
     */
    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}

