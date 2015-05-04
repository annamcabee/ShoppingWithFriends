package com.group15.shoppingwithfriends.ItemSale;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group15.shoppingwithfriends.MainWelcome.MainActivity;
import com.group15.shoppingwithfriends.R;
import com.group15.shoppingwithfriends.LoginRegistration.ZipCodeFragment;
import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.Sale;


public class AddDesiredItem extends Activity {

    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_desired_item);
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.miles_away, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);

        //database setup
        datasource = new DataSource(this);
        datasource.open();
    }

//    Spinner spinner = (Spinner) findViewById(R.id.spinner);
//    spinner.setOnItemSelectedListener(this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_desired_item, menu);
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

    /**
     * Once the register interest button is clicked, item data is saved
     * and the user goes back to the main page.
     * @param view
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void registerInterest(View view) {
        //get input info
        EditText itemNameText = (EditText) findViewById(R.id.desiredItemName);
        String itemName = itemNameText.getText().toString();

        EditText maxPriceText = (EditText) findViewById(R.id.maxPrice);
        String maxPrice = maxPriceText.getText().toString();

        EditText milesAwayText = (EditText) findViewById(R.id.milesAway);
        String milesAway = milesAwayText.getText().toString();

        EditText locationLAT = (EditText) findViewById(R.id.currentLatitude);
        String latString = locationLAT.getText().toString();
        double Lat = Double.parseDouble(latString);

        EditText locationText = (EditText) findViewById(R.id.currentLongitude);
        String longitudeString = locationText.getText().toString();
        double longitude = Double.parseDouble(longitudeString);

        //check to see if info is valid. If it isn't, alert user
        if (!Sale.itemNameIsValid(itemName)) {
            DialogFragment newFragment = new itemNameFragment();
            newFragment.show(getFragmentManager(), "Item Name Invalid");
            System.out.println("Invalid item name");
            return;
        }

        if (!Sale.itemPriceIsValid(maxPrice)) {
            DialogFragment newFragment = new ItemPriceFragment();
            newFragment.show(getFragmentManager(), "Item Price Invalid");
            System.out.println("Invalid item price");
            return;
        }

        if (!Sale.maxMilesIsValid(milesAway)) {
            DialogFragment newFragment = new MaxMilesFragment();
            newFragment.show(getFragmentManager(), "Item Price Invalid");
            System.out.println("Invalid item price");
            return;
        }

        double maxPriceDbl = Double.parseDouble(maxPrice);
        //Sale sale = datasource.createSale(itemName, maxPriceDbl,  Lat, longitude, "Walmart");
        int result = datasource.registerInterest(itemName, maxPriceDbl, Double.parseDouble(milesAway));
        /*if (result == 1) {
            //DialogFragment newFragment = new AddItemFragment();
            //newFragment.show(getFragmentManager(), "Item Added Successfully");
            //System.out.println("Item added successfully");
            Intent intent = new Intent(this, ItemsMainActivity.class);
            startActivity(intent);
        } else {
            DialogFragment newFragment = new AddItemFailed();
            newFragment.show(getFragmentManager(), "Add item failed");
            System.out.println("Item add failed");
        }*/

        if (Sale.itemNameIsValid(itemName) && Sale.itemNameIsValid(maxPrice) && Sale.maxMilesIsValid(milesAway)) {
            Intent intent = new Intent(this, ItemsMainActivity.class);
            startActivity(intent);
        }

    }

    public void cancelAddItem(View view) {
        Intent intent  = new Intent(this, ItemsMainActivity.class);
        startActivity(intent);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, ItemsMainActivity.class);
        startActivity(intent);
    }

}
