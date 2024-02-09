package com.java.designTask.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Data
public class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> itinerary;
    private List<Passenger> passengers;
    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }
    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public boolean signUpForPassengerDestinationActivity(Passenger passenger, Activity activity, Destination destination){
        Predicate<Passenger> passengerPredicate = passengers::contains;
        Predicate<Destination> destinationPredicate = d -> d.getActivities().contains(activity);
        Predicate<Activity> activityPredicate = a -> a.equals(activity);
        Function<Activity, Boolean> capacityFunction = a -> a.getCapacity() > 0;
        Function<Passenger, Boolean> canSignUpFunction = p -> p.canSignUp(activity.getCost());

        if (passengerPredicate.test(passenger) && destinationPredicate.test(destination)
                && activityPredicate.test(activity) && capacityFunction.apply(activity)
                && canSignUpFunction.apply(passenger)) {
                passenger.signUpForActivity(activity, destination);
                activity.setCapacity(activity.getCapacity() - 1);
                passenger.signUp(activity.getCost());
                return true;
        }
        return false;
    }

    public void printAvailableActivities(){
        System.out.println("Available activities: ");
        for(Destination destination: itinerary){
            List<Activity> activities = destination.getActivities();
            for(Activity activity: activities){
                if(activity.getCapacity() > 0){
                    System.out.println("Activity: "+activity.getName());
                    System.out.println("Destination: "+destination.getName());
                    System.out.println("Space Available: "+activity.getCapacity());
                }
            }
        }
    }
    public void printPassengerList(){
        System.out.println("Travel Package: "+ name);
        System.out.println("Passenger Capacity: "+passengerCapacity);
        System.out.println("Number of Passengers Enrolled: "+ passengers.size());
        System.out.println("Passenger List:");
        for(Passenger passenger : passengers){
            System.out.println("Passenger Name: "+passenger.getName()+", Passenger Number: "+passenger.getPassengerNumber());
        }
    }

    public void printItinerary(){
        System.out.println("Travel package: "+name);
        System.out.println("Itinerary:");

        for(Destination destination : itinerary){
            System.out.println("Destination: "+destination.getName());
            List<Activity> activities = destination.getActivities();
            for(Activity activity : activities){
                System.out.println("Activity: "+ activity.getName());
                System.out.println("Cost: "+activity.getCost());
                System.out.println("Capacity: "+ activity.getCapacity());
                System.out.println("Description: "+activity.getDescription());
            }
        }
    }
}
