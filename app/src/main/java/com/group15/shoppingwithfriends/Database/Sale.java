package com.group15.shoppingwithfriends.Database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kaseyclark on 2/28/15.
 */
public class Sale {

    private String user;
    private String item;
    private double price;
    private double lat;
    private double longitude;
    private String store;
    private String date;


    public Sale(){

    }

    /**
     * constructor for a sale
     *
     * @param item name
     * @param price
     * @param
     */
    public Sale(String item, double price, String store, double lat, double longitude, String date) {
        this.item = item;
        this.price = (double) Math.round(price * 100) / 100;
        this.lat = lat;
        this.longitude = longitude;
        this.store = store;
        this.date = date;
    }


    public String getUser() {
        return user;
    }

    /**
     * get item name
     *
     * @return
     */
    public String getItem() {
        return item;
    }

    /**
     * get max price
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * get location
     *
     * @return
     */
    public double getLat() {
        return lat;
    }

    /**
     * get location
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    public String getDate() {
        return date;
    }

    /**
     * getter for store variable
     * @return store
     */
    public String getStore() {
        return store;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /**
     * getter for store variable
     * @return store
     */



    public void setStore(String store) {
        this.store = store;
    }

    /**
     * setter for item
     *
     * @param item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * setter for price
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * setter for location
     *
     * @param lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
    /**
     * setter for location
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Create a string representation of a sale item that contains
     * name, price, and location
     * @return String
     */

    public String toString() {
        String value = item + " for $" + Double.toString(price) + " located at " + store;
        return value;
    }

    /**
     * If there is a letter in the item name, it is valid
     * @param str item name input by user
     * @return boolean whether the item name is valid
     */


    public static boolean itemNameIsValid(String str) {
        if (str.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine whether a numerical price was entered into the text
     * field
     * @param str maximum price input by user
     * @return boolean whether the item price is valid
     */

    public static boolean itemPriceIsValid(String str) {
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }

    }

    /**
     * Determine whether maximum miles away was entered
     * into the text field
     * @param str maximum miles away input by user
     * @return boolean whether the miles away is valid
     */

    public static boolean maxMilesIsValid(String str) {
        if (str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Determine whether the zipcode was entered and
     * is the correct length and valid
     * @param str zipcode input by user
     * @return boolean whether the zipcode is valid
     */

    public static boolean zipCodeIsValid(String str) {
        if (str.length() == 5) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean dateIsValid(String dateStr) {
        if(dateStr == null) return true;
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date date = format.parse(dateStr);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }



}