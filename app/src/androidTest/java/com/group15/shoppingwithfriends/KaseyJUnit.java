package com.group15.shoppingwithfriends;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.group15.shoppingwithfriends.ItemSale.AddDesiredItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class KaseyJUnit extends android.test.ActivityUnitTestCase<AddDesiredItem> {


    //INIT
    EditText itemNameText, maxPriceText, milesAwayText, locationLAT, locationText;

    public KaseyJUnit() { super(AddDesiredItem.class); }


    private AddDesiredItem activity;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();



    //@SetUp
    public void setUp() throws Exception {

        super.setUp();
        Intent intent = new Intent(this.getInstrumentation().getTargetContext(),
                AddDesiredItem.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    @Test
    public void testAddItemAllValid() {
        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);

        //get input info
        itemNameText.setText("xbox");
        maxPriceText.setText("50");
        milesAwayText.setText("100");
        locationLAT.setText("1");
        locationText.setText("2");

        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);

    }

    @Test
    public void testInvalidItemName() {
        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);

        //get input info
        itemNameText.setText("");
        maxPriceText.setText("50");
        milesAwayText.setText("100");
        locationLAT.setText("1");
        locationText.setText("2");

        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should have triggered after button press",
                triggeredIntent);

    }

    @Test
    public void testInvalidMaxPrice() {
        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);

        //get input info
        itemNameText.setText("barbie");
        maxPriceText.setText("");
        milesAwayText.setText("100");
        locationLAT.setText("1");
        locationText.setText("2");

        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should have triggered after button press",
                triggeredIntent);
    }

    @Test
    public void testInvalidMaxMiles() {
        EditText itemNameText = (EditText) activity.findViewById(R.id.desiredItemName);
        EditText maxPriceText = (EditText) activity.findViewById(R.id.maxPrice);
        EditText milesAwayText = (EditText) activity.findViewById(R.id.milesAway);
        EditText locationLAT = (EditText) activity.findViewById(R.id.currentLatitude);
        EditText locationText = (EditText) activity.findViewById(R.id.currentLongitude);

        //get input info
        itemNameText.setText("beer");
        maxPriceText.setText("30");
        milesAwayText.setText("");
        locationLAT.setText("1");
        locationText.setText("2");

        Button btnLaunch = (Button) activity.findViewById(R.id.buttonRI);
        assertNotNull("Button should not be null", btnLaunch);
        btnLaunch.performClick();
        Intent triggeredIntent = getStartedActivityIntent();
        assertNull("Intent should have triggered after button press",
                triggeredIntent);
    }


}