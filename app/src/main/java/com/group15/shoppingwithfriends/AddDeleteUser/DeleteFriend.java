package com.group15.shoppingwithfriends.AddDeleteUser;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.FriendsProfileList.FriendsListMain;
import com.group15.shoppingwithfriends.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Deletes a friend from a User's list of friends. The
 * delete is mutual
 */


public class DeleteFriend extends ActionBarActivity {

    private DataSource datasource;

    /**
     * on create of searching for friend to delete
     *
     * @param savedInstanceState current state
     */
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
     * This figures out what was input by the user and determines
     * which friend to delete from their list of friends
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

        if (results != null && results.size() > 0) {
            for (User userToDelete : results) {
                if (datasource.deleteFriend(userToDelete.getUsername()) == 1) {
                    System.out.println(User.getCurrentUser().getFriends());
                    DialogFragment newFragment = new UserDeleted();
                    newFragment.show(getFragmentManager(), "User deleted");
                } else {
                    DialogFragment newFragment = new DeleteUserFailedFragment();
                    newFragment.show(getFragmentManager(), "User not deleted");
                }
            }
        } else {
            DialogFragment newFragment = new DeleteUserFailedFragment();
            newFragment.show(getFragmentManager(), "User not deleted");
        }
    }

    /**
     * Return to the Main menu
     *
     * @param view
     */

    public void cancelSearch(View view) {
        Intent intent = new Intent(this, FriendsListMain.class);
        startActivity(intent);
    }
}
