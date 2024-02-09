package com.java.designTask.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Passenger {
    private String name;
    private int passengerNumber;
    private PassengerType passengerType;
    private double balance;
    private Map<Activity, Destination> signedUpActivities = new HashMap<>();
    public void signUpForActivity(Activity activity, Destination destination) {
        signedUpActivities.put(activity, destination);
    }
    public Passenger(String name, int passengerNumber, PassengerType type, double balance) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.passengerType = type;
        this.balance = balance;
    }


    public boolean canSignUp(double cost){
        switch (passengerType){
            case GOLD -> {
                return balance >= cost * 0.9;
            }
            case PREMIUM -> {
                return true;
            }
            case STANDARD -> {
                return balance >= cost;
            }
            default -> {
                return false;
            }
        }
    }

    public void signUp(double cost) {
        switch (passengerType){
            case GOLD -> {
                balance -= cost * 0.9;
            }
            case STANDARD -> {
                balance -= cost;
            }
        }
    }

    public void printDetails(){
        System.out.println("Passenger name: "+name);
        System.out.println("Passenger number: "+passengerNumber);

        if(passengerType != PassengerType.PREMIUM){
            System.out.println("Balance: "+balance);
        }

        System.out.println("Activities Signed Up: ");

        for (Map.Entry<Activity, Destination> entry : signedUpActivities.entrySet()) {
            Activity activity = entry.getKey();
            Destination destination = entry.getValue();
            double pricePaid = passengerType == PassengerType.GOLD ? activity.getCost() * 0.9 : activity.getCost();
            System.out.println("Activity Name: " + activity.getName());
            System.out.println("Destination: " + destination.getName());
            System.out.println("Price Paid: " + pricePaid);
        }
    }
}
