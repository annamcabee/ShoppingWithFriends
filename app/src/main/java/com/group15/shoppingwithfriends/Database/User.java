package com.group15.shoppingwithfriends.Database;

/**
 * Created by Travis on 2/9/2015.
 * This class creates a user object for use in the database
 */
public class User {
    private String username;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String mother;
    private String street;
    private String friends;
    private int rating;
    private int report;
    private String favorite;

    private static User currentUser;

    /**
     * constructor for user
     */
    public User() {
        username = null;
        name = null;
        password = null;
        phone = null;
        email = null;
        mother = null;
        street = null;
        friends = null;
    }

    /**
     * constructor for username
     *
     * @param username username
     * @param name     name
     * @param password password
     * @param phone    phone
     * @param email    email
     * @param mother   mother
     * @param street   street
     * @param friends  friends
     */
    public User(String username, String name, String password, String phone,
                String email, String mother, String street, String friends, String favorite) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.mother = mother;
        this.street = street;
        this.friends = friends;
        this.favorite = favorite;
    }

    /**
     * sets current user
     *
     * @param current current user
     */
    public static void setCurrentUser(User current) {
        currentUser = current;
    }

    /**
     * gets the curent user
     *
     * @return current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * gets the user name
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets user name
     *
     * @param username username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name
     *
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     *
     * @param password you want to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets phone number
     *
     * @return number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets phone number
     *
     * @param phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * gets 1st security question answer
     *
     * @return answer
     */
    public String getMother() {
        return mother;
    }

    /**
     * sets 1st security question answer
     *
     * @param mother maiden name
     */
    public void setMother(String mother) {
        this.mother = mother;
    }

    /**
     * gets the street answer, for 2nd security question
     *
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * sets 2nd sec question answer
     *
     * @param friends
     */
    public void setFriends(String friends) {
        this.friends = friends;
    }

    /**
     * gets friends
     *
     * @return friends
     */
    public String getFriends() {
        return friends;
    }

    /**
     * gets the second sec question answer
     *
     * @return answer
     */
    public void setStreet(String street) {
        this.street = street;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public void setFavorite(String favorite) { this.favorite = favorite; }

    public String getFavorite() { return favorite; }


    // Will be used by the ArrayAdapter in the ListView
    @Override
    /**
     * this will be used by the ArrayAdapter in the ListView
     * @return string
     */
    public String toString() {
        String user = username;
        return user;
    }

    /**
     * check to see if the email is valid
     *
     * @param email string
     * @return true if password is valid
     */
    public static boolean isEmailValid(String email) {
        return email.contains("@") && email.length() > 4;
    }

    /**
     * check to see if the password is valid
     *
     * @param password string
     * @return true if password is valid
     */
    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * to check if the phone number has a valid length
     *
     * @param number string
     * @return true if phone num is length 10
     */
    public static boolean isPhoneNumberValid(String number) {
        if (number.length() != 10) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks to see if the username is valid
     *
     * @param username given username
     * @return true if valid
     */
    public static boolean isUsernameValid(String username) {
        return (!username.contains(",") && !username.contains(" "));
    }
}
