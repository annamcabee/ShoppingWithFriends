package com.group15.shoppingwithfriends.Database;

/**
 * Created by Travis on 3/24/2015.
 */
public class Interest {

    private String user;
    private String item;
    private double price;
    private double distance;

    public void setUser(String user) {
        this.user = user;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getUser() {
        return user;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Interested in a " + item + " at max price " + price + " located less that " + distance + " miles away.";
    }
}
