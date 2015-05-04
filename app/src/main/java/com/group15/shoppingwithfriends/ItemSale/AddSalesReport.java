package com.group15.shoppingwithfriends.ItemSale;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.Sale;
import com.group15.shoppingwithfriends.PushNotifcations.NewMessageNotification;
import com.group15.shoppingwithfriends.R;


public class AddSalesReport extends Activity {

    private DataSource datasource;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_report);
        datasource = new DataSource(this);
        datasource.open();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_sales_report, menu);
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void submitSalesReport(View view) {
        //get input info
        EditText itemNameText = (EditText) findViewById(R.id.sales_report_item_name);
        String itemName = itemNameText.getText().toString();

        EditText priceText = (EditText) findViewById(R.id.sales_report_item_price);
        String price = priceText.getText().toString();
        System.out.println(price);
        double priceDbl = Double.parseDouble(price);

        EditText storeNameText = (EditText) findViewById(R.id.sales_report_store_name);
        String store = storeNameText.getText().toString();

        EditText locationLAT = (EditText) findViewById(R.id.sales_report_location_latitude);
        String Lat = locationLAT.getText().toString();
        double latDouble = Double.parseDouble(Lat);

        EditText locationText = (EditText) findViewById(R.id.sales_report_location_longitude);
        String longitude = locationText.getText().toString();
        double lonDouble = Double.parseDouble(longitude);

        EditText exDateText = (EditText) findViewById(R.id.salesReportEx);
        String exDate = exDateText.getText().toString();

        if(!Sale.dateIsValid(exDate)){
            exDate = null;
            System.out.println("Invalid Date");
            //Make this a pop up box instead
        }

        Sale sale = datasource.createSale(itemName, priceDbl, latDouble, lonDouble, store, exDate);


        if (sale.getItem() != null) {
            DialogFragment newFragment = new AddSalesReportFragment();
            newFragment.show(getFragmentManager(), "Item Added Successfully");
            System.out.println("Item added successfully");
            NewMessageNotification notification = new NewMessageNotification();
            notification.notify(getApplicationContext(), "New Sales Report", 1);
        } else {
            DialogFragment newFragment = new AddSalesReportFailed();
            newFragment.show(getFragmentManager(), "Add item failed");
            System.out.println("Item add failed");
        }

    }

    public void cancelSalesReport(View view) {
        Intent intent  = new Intent(this, SalesReportMainActivity.class);
        startActivity(intent);
    }

    public void goBackToMain(View view) {
        Intent intent = new Intent(this, SalesReportMainActivity.class);
        startActivity(intent);
    }
}
