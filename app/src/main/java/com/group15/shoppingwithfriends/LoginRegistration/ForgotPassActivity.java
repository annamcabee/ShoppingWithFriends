package com.group15.shoppingwithfriends.LoginRegistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group15.shoppingwithfriends.R;

public class ForgotPassActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_pass, menu);
        return true;
    }

    @Override
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

    public void cancel(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void sendEmail(View view) {
        EditText usernameForgotText = (EditText) findViewById(R.id.usernameForgot);
        String usernameForgot = usernameForgotText.getText().toString();

        EditText maidenNameForgotText = (EditText) findViewById(R.id.maidenNameForgot);
        String maidenNameForgot = maidenNameForgotText.getText().toString();

        EditText streetNameForgotText = (EditText) findViewById(R.id.streetNameForgot);
        String streetNameForgot = streetNameForgotText.getText().toString();

        try {
            /*GMailSender sender = new GMailSender("travishanly@gmail.com", "Alphatau0mega");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "travishanly@gmail.com",
                    "travishanly@gmail.com");
            System.out.println("here");*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
