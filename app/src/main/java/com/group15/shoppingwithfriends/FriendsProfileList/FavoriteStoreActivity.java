package com.group15.shoppingwithfriends.FriendsProfileList;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.LoginRegistration.USInvalidFragment;
import com.group15.shoppingwithfriends.MainWelcome.ViewMyProfileActivity;
import com.group15.shoppingwithfriends.R;

public class FavoriteStoreActivity extends Activity {
    private DataSource datasource;
    private static final int REQUEST_CODE = 100;
    private static User currUser;

    public void setCurr(User u) {
        this.currUser = u;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_store);
        datasource = new DataSource(this);
        datasource.open();
        currUser = User.getCurrentUser();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorite_store, menu);
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

    public void goBackToMain(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
       startActivity(intent);
    }

    public void registerFavoriteStore(View view) {
        EditText storeName = (EditText) findViewById(R.id.favoriteStore);
        String storeNameText = storeName.getText().toString();
        currUser.setFavorite(storeNameText);
        if (currUser.getFavorite() != null) {
            DialogFragment newFragment = new FavoriteStoreAdded();
            newFragment.show(getFragmentManager(), "Favorite store added");
        }
    }
}
