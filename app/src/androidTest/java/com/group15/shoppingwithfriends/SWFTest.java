package com.group15.shoppingwithfriends;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.Database.User;
import com.group15.shoppingwithfriends.ItemSale.AddSalesReport;

import org.junit.Test;


/**
 * Created by Travis Hanly on 3/23/15.
 */
public class SWFTest extends
        android.test.ActivityUnitTestCase<AddSalesReport>{

    public SWFTest() {
        super(AddSalesReport.class);
    }

    private AddSalesReport activity;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getInstrumentation().getTargetContext(),
                AddSalesReport.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    /**
     * Rachel Martin's test for registerInterest in the AddSalesReport Activity
     * @throws Exception
     */
    @Test
    public void testValidSale() {

        User user = new User("Travis", "Travis", "Travis", "9000000000", "email@gmail.com", "Marie", "Elm", "");
        User.setCurrentUser(user);

        EditText itemNameText = (EditText) activity.findViewById(R.id.sales_report_item_name);
        itemNameText.setText("laptop");

        EditText priceText = (EditText) activity.findViewById(R.id.sales_report_item_price);
        priceText.setText("1000");

        EditText storeNameText = (EditText) activity.findViewById(R.id.sales_report_store_name);
        storeNameText.setText("10");

        EditText locationLat = (EditText) activity.findViewById(R.id.sales_report_location_latitude);
        locationLat.setText("34");

        EditText locationLong = (EditText) activity.findViewById(R.id.sales_report_location_longitude);
        locationLong.setText("34");

        Button btnLaunch = (Button) activity.findViewById(R.id.button_sales_report);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);

    }

    public void testInvalidSale() {

        EditText itemNameText = (EditText) activity.findViewById(R.id.sales_report_item_name);
        itemNameText.setText("laptop");

        EditText priceText = (EditText) activity.findViewById(R.id.sales_report_item_price);
        priceText.setText("1000");

        EditText storeNameText = (EditText) activity.findViewById(R.id.sales_report_store_name);
        storeNameText.setText("10");

        EditText locationLat = (EditText) activity.findViewById(R.id.sales_report_location_latitude);
        locationLat.setText("34");

        EditText locationLong = (EditText) activity.findViewById(R.id.sales_report_location_longitude);
        locationLong.setText("34");

        Button btnLaunch = (Button) activity.findViewById(R.id.button_sales_report);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should have triggered after button press",
                triggeredIntent);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
