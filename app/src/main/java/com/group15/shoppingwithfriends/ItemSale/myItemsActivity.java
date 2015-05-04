package com.group15.shoppingwithfriends.ItemSale;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.Interest;
import com.group15.shoppingwithfriends.Database.Sale;
import com.group15.shoppingwithfriends.MainWelcome.MainActivity;
import com.group15.shoppingwithfriends.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annamcabee on 3/5/15.
 * <p/>
 * class for my items
 */
public class myItemsActivity extends ListActivity {
    private DataSource datasource;
    @Override
    /**
     * method handles all that is done when activity is created. It launches the
     *  my items fragment
     * @param Bundle saved instance state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);
        datasource = new DataSource(this);
        datasource.open();
        super.onCreate(savedInstanceState);
        List<Interest> itemsList = new ArrayList();
        itemsList = datasource.getAllInterest();
        ArrayAdapter<Interest> adapter = new ArrayAdapter<Interest>(this, android.R.layout.simple_list_item_1, itemsList);
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
        startActivity(new Intent(this, ItemsMainActivity.class));
    }

}

