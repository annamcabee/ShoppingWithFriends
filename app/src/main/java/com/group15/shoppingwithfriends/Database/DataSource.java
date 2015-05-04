package com.group15.shoppingwithfriends.Database;

/**
 * Created by Travis on 2/9/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    private String[] allUserColumns = {
            SQLiteHelper.COLUMN_USERNAME,
            SQLiteHelper.COLUMN_PASSWORD,
            SQLiteHelper.COLUMN_NAME,
            SQLiteHelper.COLUMN_PHONE,
            SQLiteHelper.COLUMN_EMAIL,
            SQLiteHelper.COLUMN_MOTHER,
            SQLiteHelper.COLUMN_STREET,
            SQLiteHelper.COLUMN_RATING,
            SQLiteHelper.COLUMN_REPORT,
            SQLiteHelper.COLUMN_FAVORITE};

    private String[] allFriendColumns = {
            SQLiteHelper.COLUMN_USER,
            SQLiteHelper.COLUMN_FRIEND};

    private String[] allSaleColumns = {
            SQLiteHelper.COLUMN_USER,
            SQLiteHelper.COLUMN_ITEM,
            SQLiteHelper.COLUMN_PRICE,
            SQLiteHelper.COLUMN_LAT,
            SQLiteHelper.COLUMN_LONGITUDE,
            SQLiteHelper.COLUMN_STORE,
            SQLiteHelper.COLUMN_DATE};

    private String[] allInterestColumns = {
            SQLiteHelper.COLUMN_USER,
            SQLiteHelper.COLUMN_ITEM,
            SQLiteHelper.COLUMN_PRICE,
            SQLiteHelper.COLUMN_DISTANCE};

    public DataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /*Inserts a new user into the database
    @param username
    @param password
    @param name
    @param phone
    @param email
    @param mother
    @param street
    @return the new user created in the database, null if no user is created
     */
    public User createUser(String username, String password, String name, String phone,
                           String email, String mother, String street) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_USERNAME, username);
        values.put(SQLiteHelper.COLUMN_PASSWORD, password);
        values.put(SQLiteHelper.COLUMN_NAME, name);
        values.put(SQLiteHelper.COLUMN_PHONE, phone);
        values.put(SQLiteHelper.COLUMN_EMAIL, email);
        values.put(SQLiteHelper.COLUMN_MOTHER, mother);
        values.put(SQLiteHelper.COLUMN_STREET, street);
        values.put(SQLiteHelper.COLUMN_RATING, 5);
        values.put(SQLiteHelper.COLUMN_REPORT, 0);
        String favorite = null;
        values.put(SQLiteHelper.COLUMN_FAVORITE, favorite);
        long insertId = database.insert(SQLiteHelper.TABLE_USER, null, values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                allUserColumns, SQLiteHelper.COLUMN_USERNAME + "='" + username + "'", null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    /**
     * create user helper method for facebook
     * @param username
     * @param name
     * @param email
     * @return
     */
    public User createUser(String username, String name, String email) {
        return createUser(username, "", name, "", email, "", "");
    }

    /**
     * create sale method
     * @param item
     * @param price
     * @param lat
     * @param longitude
     * @param store
     * @param date
     * @return the sale
     */
    public Sale createSale(String item, double price, double lat, double longitude, String store, String date) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_USER, User.getCurrentUser().getUsername());
        values.put(SQLiteHelper.COLUMN_ITEM, item);
        values.put(SQLiteHelper.COLUMN_PRICE, price);
        values.put(SQLiteHelper.COLUMN_LAT, lat);
        values.put(SQLiteHelper.COLUMN_LONGITUDE, longitude);
        values.put(SQLiteHelper.COLUMN_STORE, store);
        values.put(SQLiteHelper.COLUMN_DATE, date);
        long insertId = database.insert(SQLiteHelper.TABLE_SALE, null, values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_SALE, allSaleColumns,
                SQLiteHelper.COLUMN_ITEM + "='" + item + "' and "
                        + SQLiteHelper.COLUMN_PRICE + "='" + price + "' and "
                        + SQLiteHelper.COLUMN_LAT + "='" + lat + "' and "
                        + SQLiteHelper.COLUMN_LONGITUDE + "='" + longitude + "' and "
                        + SQLiteHelper.COLUMN_STORE + "='" + store + "' and "
                        + SQLiteHelper.COLUMN_DATE + "='" + date + "'",
                null, null, null, null);
        cursor.moveToFirst();
        System.out.println(date);
        Sale newSale = cursorToSale(cursor);
        cursor.close();
        return newSale;
    }

    /*Inserts a new sale in the database
    @param item
    @param price
    @param location
    @return 1 if successful, 0 else
    */
    public int registerInterest(String item, double price, double distance) {
        if (User.getCurrentUser() == null) return 0;
        String username = User.getCurrentUser().getUsername();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_USER, username);
        values.put(SQLiteHelper.COLUMN_ITEM, item);
        values.put(SQLiteHelper.COLUMN_PRICE, price);
        values.put(SQLiteHelper.COLUMN_DISTANCE, distance);
        long id = database.insert(SQLiteHelper.TABLE_INTEREST, null, values);
        if (id == -1) return 0;
        return 1;
    }

    /**
     * Removes a user from the databse
     * @param user
     */
    public void deleteUser(User user) {
        String username = user.getUsername();
        database.delete(SQLiteHelper.TABLE_USER, SQLiteHelper.COLUMN_USERNAME
                + " ='" + username + "'", null);
    }

    /**
     * Returns whether or not a user is registered
     * @param username
     * @return
     */
    public boolean userExists(String username) {
        Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                null, SQLiteHelper.COLUMN_USERNAME + "='" + username + "'", null,
                null, null, null);
        cursor.moveToLast();
        int count = cursor.getCount();
        cursor.close();
        if (count == 0) {
            return false;
        }
        return true;
    }

    public List<Sale> availableInterests(){
        for(Sale sale : getAllSales()){
            System.out.println(sale);
        }
        ArrayList<Interest> interests = (ArrayList<Interest>) getAllInterest();
        ArrayList<Sale> available = new ArrayList<Sale>();
        for(Interest interest : interests){
            ArrayList<Sale> sales = (ArrayList<Sale>) getAllSales(interest.getItem(), interest.getPrice());
            for(Sale sale : sales){
                if(isFriend(sale.getUser()) || sale.getUser().equals(User.getCurrentUser().getUsername())){
                    if(!available.contains(sale)){
                        available.add(sale);
                    }
                }
            }
        }
        Date currentDate = new Date();
        for(Sale sale : available){
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date date = format.parse(sale.getDate());
                int comparison = currentDate.compareTo(date);
                if(comparison > 0) {
                    removeSale(sale);
                    available.remove(sale);
                }
            }
            catch(ParseException e) {
                sale.setDate(null);
            }
        }
        return available;
    }


    private void removeSale(Sale sale) {
        String user = sale.getUser();
        String item = sale.getItem();
        double price = sale.getPrice();
        double lat = sale.getLat();
        double longitude = sale.getLongitude();
        String store = sale.getStore();
        String date = sale.getDate();
        database.delete(SQLiteHelper.TABLE_SALE,
                SQLiteHelper.COLUMN_ITEM + "='" + item + "' and "
                + SQLiteHelper.COLUMN_PRICE + "='" + price + "' and "
                + SQLiteHelper.COLUMN_LAT + "='" + lat + "' and "
                + SQLiteHelper.COLUMN_LONGITUDE + "='" + longitude + "' and "
                + SQLiteHelper.COLUMN_STORE + "='" + store + "' and "
                + SQLiteHelper.COLUMN_DATE + "='" + date + "'", null);
    }

    //returns 1 if successful
    //0 else
    public int addFriend(String friend) {
        if (User.getCurrentUser() == null) return 0;
        if (isFriend(friend)) return 0;
        String username = User.getCurrentUser().getUsername();
        ContentValues values1 = new ContentValues();
        values1.put(SQLiteHelper.COLUMN_USER, username);
        values1.put(SQLiteHelper.COLUMN_FRIEND, friend);
        ContentValues values2 = new ContentValues();
        values2.put(SQLiteHelper.COLUMN_USER, friend);
        values2.put(SQLiteHelper.COLUMN_FRIEND, username);
        try {
            database.insert(SQLiteHelper.TABLE_FRIEND, null, values1);
            database.insert(SQLiteHelper.TABLE_FRIEND, null, values2);
            return 1;
        } catch (Exception e) {
            System.out.println("Exception in addFriend");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /**
     * REmoves a friend from the current users friend list
     * @param friend
     * @return
     */
    public int deleteFriend(String friend) {
        if (User.getCurrentUser() == null) return 0;
        if (!isFriend(friend)) return 0;
        String username = User.getCurrentUser().getUsername();
        try {
            database.delete(SQLiteHelper.TABLE_FRIEND, SQLiteHelper.COLUMN_USER + " ='" + username + "' and "
                    + SQLiteHelper.COLUMN_FRIEND + " ='" + friend + "'", null);
            database.delete(SQLiteHelper.TABLE_FRIEND, SQLiteHelper.COLUMN_USER + " ='" + friend + "' and "
                    + SQLiteHelper.COLUMN_FRIEND + " ='" + username + "'", null);
            return 1;
        } catch (Exception e) {
            System.out.println("Exception in deleteFriend");
            System.out.println(e.getMessage());
            return 0;
        }
    }

    /**
     * Returns whether or not a user is friends with the passed in username
     * @param friend
     * @return
     */
    private boolean isFriend(String friend) {
        if (User.getCurrentUser() == null) return false;
        String username = User.getCurrentUser().getUsername();
        Cursor cursor = database.query(SQLiteHelper.TABLE_FRIEND,
                allFriendColumns, SQLiteHelper.COLUMN_USER + " ='" + username + "' and "
                        + SQLiteHelper.COLUMN_FRIEND + " ='" + friend + "'", null,
                null, null, null);
        cursor.moveToLast();
        int count = cursor.getCount();
        cursor.close();
        if (count == 0) {
            return false;
        }
        return true;
    }

    /**
     * Returns all the friends of the logged in user
     * @return
     */
    public List<User> getFriendsList() {
        List<User> friends = new ArrayList<User>();
        if (User.getCurrentUser() == null) return friends;
        String username = User.getCurrentUser().getUsername();
        String[] column = {SQLiteHelper.COLUMN_FRIEND};
        Cursor cursor = database.query(SQLiteHelper.TABLE_FRIEND,
                column, SQLiteHelper.COLUMN_USER + "='" + username + "'", null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = getUser(cursor.getString(0));
            friends.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return friends;
    }

    /**
     * Returns a user object for a given username
     * @param username
     * @return
     */
    public User getUser(String username) {
        Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                allUserColumns, SQLiteHelper.COLUMN_USERNAME + "='" + username + "'", null,
                null, null, null);
        cursor.moveToFirst();
        User user = cursorToUser(cursor);
        cursor.close();
        return user;
    }

    /**
     * Gets a users password
     * @param username
     * @return
     */
    public String getPassword(String username) {
        String[] column = {SQLiteHelper.COLUMN_PASSWORD};
        Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                column, SQLiteHelper.COLUMN_USERNAME + "='" + username + "'", null,
                null, null, null);
        cursor.moveToLast();
        int count = cursor.getCount();
        if (count == 0) {
            return null;
        }
        String password = cursor.getString(0);
        cursor.close();
        return password;
    }

    /**
     * Returns a list of all registered users
     * @return
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                allUserColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    /**
     * Gets all sales less than or equal to the maxPrice
     * @param maxPrice
     * @return
     */
    public List<Sale> getAllSales(String item, double maxPrice) {
        List<Sale> sales = new ArrayList<Sale>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_SALE,
                null, SQLiteHelper.COLUMN_PRICE + "<='" + maxPrice + "' and " +
                        SQLiteHelper.COLUMN_ITEM + " ='" + item + "'", null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Sale sale = cursorToSale(cursor);
            sales.add(sale);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return sales;
    }

    /**
     * Returns a list of all the registered sales
     * @return
     */
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<Sale>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_SALE,
                allSaleColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Sale sale = cursorToSale(cursor);
            sales.add(sale);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return sales;
    }

    /**
     * Returns a list of all of the sales that the current user is interested in
     * @return
     */
    public List<Interest> getAllInterest() {
        List<Interest> interests = new ArrayList<Interest>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_INTEREST,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Interest interest = cursorToInterest(cursor);
            interests.add(interest);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return interests;
    }

    /**
     * Searches the users and returns a list that match the search term
     * The term can be en email, username, phone number
     * @param term
     * @return
     */
    public List<User> search(String term) {

        List<User> suggestions = new ArrayList<User>();

        if (User.isEmailValid(term)) {
            Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                    allUserColumns, SQLiteHelper.COLUMN_EMAIL + "='" + term + "'", null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = cursorToUser(cursor);
                suggestions.add(user);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }

        if (User.isPhoneNumberValid(term)) {
            Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                    allUserColumns, SQLiteHelper.COLUMN_PHONE + "='" + term + "'", null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = cursorToUser(cursor);
                suggestions.add(user);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }

        if (User.isUsernameValid(term)) {
            Cursor cursor = database.query(SQLiteHelper.TABLE_USER,
                    allUserColumns, SQLiteHelper.COLUMN_USERNAME + "='" + term + "'", null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = cursorToUser(cursor);
                suggestions.add(user);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
        }

        if (suggestions.size() == 0) return null;

        return suggestions;
    }

    private User cursorToUser(Cursor cursor) {
        if (cursor.isAfterLast()) return null;
        User user = new User();
        user.setUsername(cursor.getString(0));
        user.setPassword(cursor.getString(1));
        user.setName(cursor.getString(2));
        user.setPhone(cursor.getString(3));
        user.setEmail(cursor.getString(4));
        user.setMother(cursor.getString(5));
        user.setStreet(cursor.getString(6));
        user.setRating(cursor.getInt(7));
        user.setReport(cursor.getInt(8));
        user.setFavorite(cursor.getString(9));
        return user;
    }

    private Sale cursorToSale(Cursor cursor) {
        if (cursor.isAfterLast()) return null;
        Sale sale = new Sale();
        sale.setUser(cursor.getString(0));
        sale.setItem(cursor.getString(1));
        sale.setPrice(cursor.getFloat(2));
        sale.setLat(cursor.getFloat(3));
        sale.setLongitude(cursor.getFloat(4));
        sale.setStore(cursor.getString(5));
        sale.setDate(cursor.getString(6));
        return sale;
    }

    private Interest cursorToInterest(Cursor cursor) {
        if (cursor.isAfterLast()) return null;
        Interest interest = new Interest();
        interest.setUser(cursor.getString(0));
        interest.setItem(cursor.getString(1));
        interest.setPrice(cursor.getDouble(2));
        interest.setDistance(cursor.getDouble(3));
        return interest;
    }

}
