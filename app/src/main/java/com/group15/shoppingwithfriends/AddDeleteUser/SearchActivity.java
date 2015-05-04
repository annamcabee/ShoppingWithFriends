package com.group15.shoppingwithfriends.AddDeleteUser;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.FriendsProfileList.FriendsListMain;
import com.group15.shoppingwithfriends.MainWelcome.MainActivity;
import com.group15.shoppingwithfriends.R;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends ActionBarActivity {

    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        datasource = new DataSource(this);
        datasource.open();
    }


    @Override
    /**
     * this adds items to the action bar if it is present
     * @param menu
     * @return boolean on if inflated menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    /**
     * Handle action bar item clicks here
     * @param MenuItem item
     * @return boolean on if item is selected
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
     * Finds friend to add based on user input of username, password, phone
     * number, or email. Adds them to the user's list of friends
     *
     * @param view
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void getInfo(View view) {

        EditText searchName = (EditText) findViewById(R.id.search_by_name);
        String searchNameText = searchName.getText().toString();

        List<User> results = new ArrayList<>();

        if (searchNameText != null) {
            if (datasource.search(searchNameText) != null) {
                results.addAll(datasource.search(searchNameText));
            }
        } else {
            DialogFragment newFragment = new NoDataFragment();
            newFragment.show(getFragmentManager(), "No data entered");
            results = null;
        }

        if (results.size() > 0) {
            for (User userToAdd : results) {
                int added = datasource.addFriend(userToAdd.getUsername());
                if (added == 1) {
                    DialogFragment newFragment = new AddUserFragment();
                    newFragment.show(getFragmentManager(), "User added");
                } else {
                    DialogFragment newFragment = new AddUserFailedFragment();
                    newFragment.show(getFragmentManager(), "User not added");
                }
            }
        } else {
            DialogFragment newFragment = new AddUserFailedFragment();
            newFragment.show(getFragmentManager(), "User not added");
        }
    }

    /**
     * cancel button takes the User back to Main page
     *
     * @param view
     */
    public void cancelSearch(View view) {
        Intent intent = new Intent(this, FriendsListMain.class);
        startActivity(intent);
    }
}
