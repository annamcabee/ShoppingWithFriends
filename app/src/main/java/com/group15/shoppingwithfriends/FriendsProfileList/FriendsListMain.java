package com.group15.shoppingwithfriends.FriendsProfileList;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.group15.shoppingwithfriends.AddDeleteUser.DeleteFriend;
import com.group15.shoppingwithfriends.AddDeleteUser.SearchActivity;
import com.group15.shoppingwithfriends.MainWelcome.MainActivity;
import com.group15.shoppingwithfriends.R;

public class FriendsListMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends_list_main, menu);
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

    public void goToFriendsList(View view){
        Intent intent = new Intent(this, FriendsListActivity.class);
        startActivity(intent);
    }

    /**
     * got to AddFriend page
     * @param view
     */

    public void goToAddFriend(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /** go to delete user page
     *
     * @param view
     */

    public void goToDeleteFriend(View view) {
        Intent intent = new Intent(this, DeleteFriend.class);
        startActivity(intent);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
