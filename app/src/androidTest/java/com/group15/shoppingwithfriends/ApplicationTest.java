package com.group15.shoppingwithfriends;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.group15.shoppingwithfriends.Database.DataSource;
import com.group15.shoppingwithfriends.ItemSale.AddDesiredItem;

import org.junit.Test;


/**
 * Created by rachelmartin on 3/23/15.
 */
public class ApplicationTest extends
        android.test.ActivityUnitTestCase<AddDesiredItem>{

    public ApplicationTest() {
        super(AddDesiredItem.class);
    }

    private AddDesiredItem activity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getInstrumentation().getTargetContext(),
                AddDesiredItem.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    /**
     * Travis Hanly's test for registerInterest in the AddDesiredItem Activity
     * @throws Exception
     */
    @Test
    public void testValidRegisterInterest() {

        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        itemNameText.setText("laptop");

        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        maxPriceText.setText("1000");

        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        milesAwayText.setText("10");

        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        locationLAT.setText("34");

        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);
        locationText.setText("34");

        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);

    }

    public void testInvalidNameRegisterInterest() {
        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        itemNameText.setText("");

        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        maxPriceText.setText("1000");
        double maxPriceDbl = Double.parseDouble("1000");

        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        milesAwayText.setText("10");

        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        locationLAT.setText("34");
        double Lat = Double.parseDouble("34");

        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);
        locationText.setText("34");
        double longitude = Double.parseDouble("34");

        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should not have triggered after button press", triggeredIntent);
    }

    public void testInvalidPriceRegisterInterest() {
        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        itemNameText.setText("laptop");

        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        maxPriceText.setText("");

        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        milesAwayText.setText("10");

        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        locationLAT.setText("34");
        double Lat = Double.parseDouble("34");

        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);
        locationText.setText("34");
        double longitude = Double.parseDouble("34");


        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should not have triggered after button press", triggeredIntent);
    }

    public void testInvalidMilesAwayRegisterInterest() {
        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        itemNameText.setText("laptop");

        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        maxPriceText.setText("10");

        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        milesAwayText.setText("");

        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        locationLAT.setText("34");
        double Lat = Double.parseDouble("34");

        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);
        locationText.setText("34");
        double longitude = Double.parseDouble("34");


        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should not have triggered after button press", triggeredIntent);
    }



    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
