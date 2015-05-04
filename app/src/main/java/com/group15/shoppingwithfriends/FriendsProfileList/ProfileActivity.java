package com.group15.shoppingwithfriends.FriendsProfileList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.MainWelcome.MainActivity;
import com.group15.shoppingwithfriends.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by annamcabee on 2/23/15.
 */
public class ProfileActivity extends ListActivity {
    private DataSource datasource;
    private static final int REQUEST_CODE = 100;
    private static User currUser;

    public void setCurr(User u) {
        this.currUser = u;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        datasource = new DataSource(this);
        datasource.open();
        currUser = User.getCurrentUser();
        super.onCreate(savedInstanceState);
        List userInfo = new ArrayList();
        userInfo.add("Username: " + currUser.getUsername());
        userInfo.add("Email: " + currUser.getEmail());
        userInfo.add("Phone Number: "+ currUser.getPhone());
        userInfo.add("Rating: " + currUser.getRating());
        userInfo.add("Report Count: " + currUser.getReport());
        userInfo.add("Favorite Store: " + currUser.getFavorite());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userInfo);
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
    public void goBackToMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String userName = data.getStringExtra("username");
            Toast.makeText(this, "Username: " + userName, Toast.LENGTH_SHORT).show();
        }
    }

    public void addFavoriteStore(View view) {
        Intent intent = new Intent(this, FavoriteStoreActivity.class);
        startActivity(intent);
    }

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

}
