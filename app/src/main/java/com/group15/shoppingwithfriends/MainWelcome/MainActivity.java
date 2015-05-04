package com.group15.shoppingwithfriends.MainWelcome;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.group15.shoppingwithfriends.AddDeleteUser.DeleteFriend;
import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.FriendsProfileList.ProfileActivity;
import com.group15.shoppingwithfriends.ItemSale.AddDesiredItem;
import com.group15.shoppingwithfriends.ItemSale.AddSalesReport;
import com.group15.shoppingwithfriends.ItemSale.ItemsMainActivity;
import com.group15.shoppingwithfriends.ItemSale.SalesReportMainActivity;
import com.group15.shoppingwithfriends.LoginRegistration.LoginActivity;
import com.group15.shoppingwithfriends.Map.DisplayMap;
import com.group15.shoppingwithfriends.R;
import com.group15.shoppingwithfriends.AddDeleteUser.SearchActivity;
import com.group15.shoppingwithfriends.ItemSale.SalesReportFragment;
import com.group15.shoppingwithfriends.FriendsProfileList.FriendsListMain;


public class MainActivity extends ActionBarActivity {

    private DataSource datasource;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        datasource = new DataSource(this);
        datasource.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (datasource.availableInterests().size() > 0) {
            DialogFragment newFragment = new SalesReportFragment();
            newFragment.show(getFragmentManager(), "Sales report created");
            return;
        }


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**log out redirects to Welcome screen
     *
     * @param view
     */
   public void logOut(View view) {
        User.setCurrentUser(null);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
   }

    /**
     * go to Friends List
     * @param view
     */
    public void goToFriends(View view){
        Intent intent = new Intent(this, FriendsListMain.class);
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

    public void goToDeleteUser(View view) {
        Intent intent = new Intent(this, DeleteFriend.class);
        startActivity(intent);
    }

    /** go to delete user page
     *
     * @param view
     */

    public void goToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * directs ot sales report
     * @param view
     */
    public void goToAddSalesReport(View view) {
        Intent intent = new Intent(this, AddSalesReport.class);
        startActivity(intent);
    }


    /**
     * go to add desired item to list
     * @param view
     */
    public void addItemToList(View view){
        Intent intent = new Intent(this, AddDesiredItem.class);
        startActivity(intent);
    }

    /**
     * go to view items on wishlist
     * @param view
     */
    public void goToMyItems(View view) {
        Intent intent = new Intent(this, ItemsMainActivity.class);
        startActivity(intent);
    }

    /**
     * go to view sales reports
     * @param view
     */

    public void goToViewSalesReports(View view) {
        Intent intent = new Intent(this, SalesReportMainActivity.class);
        startActivity(intent);
    }

    /**
     * go to view sales reports
     * @param view
     */

    public void goToViewMap(View view) {
        Intent intent = new Intent(this, DisplayMap.class);
        startActivity(intent);
    }

    public void goToViewProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

}
