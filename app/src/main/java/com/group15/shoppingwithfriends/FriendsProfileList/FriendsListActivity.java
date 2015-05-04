package com.group15.shoppingwithfriends.FriendsProfileList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.ItemSale.ItemsMainActivity;
import com.group15.shoppingwithfriends.MainWelcome.MainActivity;
import com.group15.shoppingwithfriends.R;

import java.util.List;


/**
 * Opens the fragment containing the list view of a user's
 * friends
 * Created by rachelmartin on 2/19/15.
 */
public class FriendsListActivity extends ListActivity {
    private List<User> friendsList;
    private DataSource datasource;
    private static final int REQUEST_CODE = 100;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        datasource = new DataSource(this);
        datasource.open();
        super.onCreate(savedInstanceState);
        friendsList = datasource.getFriendsList();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, friendsList);
        setListAdapter(adapter);
    }

    /**
     * handles interaction with fragment
     *
     * @param id string
     */
    public void onFragmentInteraction(String id) {

    }

    /**
     * when back button clicked, returns to previous page,
     * Main Activity
     * @param view
     */
    public void backToMain(View view) {
        startActivity(new Intent(this, FriendsListMain.class));
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String userName = data.getStringExtra("username");
            Toast.makeText(this, "Username: " + userName, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        User user = friendsList.get(position);
        Intent intent = new Intent(this, ProfileActivity.class);
        ProfileActivity n = new ProfileActivity();
        n.setCurr(user);
        startActivity(intent);
    }

    public void goBackToMain(View view) {
        Intent intent = new Intent(this, FriendsListMain.class);
        startActivity(intent);
    }

}
