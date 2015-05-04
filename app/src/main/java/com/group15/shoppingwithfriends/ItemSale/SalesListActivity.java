package com.group15.shoppingwithfriends.ItemSale;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.Sale;
import com.group15.shoppingwithfriends.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Opens the fragment containing the list view of a user's
 * friends
 * Created by rachelmartin on 3/12/15.
 */
public class SalesListActivity extends ListActivity {
    private DataSource datasource;
    @Override
    /**
     * method handles all that is done when activity is created. It launches the
     * friends list fragment
     * @param Bundle saved instance state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_list);
        datasource = new DataSource(this);
        datasource.open();
        super.onCreate(savedInstanceState);
        List<Sale> salesList = new ArrayList();
        salesList = datasource.availableInterests();
        ArrayAdapter<Sale> adapter = new ArrayAdapter<Sale>(this, android.R.layout.simple_list_item_1, salesList);
        setListAdapter(adapter);
    }

    public void goBackToMain(View view) {
        Intent intent = new Intent(this, SalesReportMainActivity.class);
        startActivity(intent);
    }



}
