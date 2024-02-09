package com.java.designTask;

import com.java.designTask.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DesignTaskApplicationTests {


	@Test
	public void testSignUpForActivity() {
		// Create some activities
		Activity hiking = new Activity("Hiking", "Enjoy nature on a hiking trail", 20.0, 10);
		Activity snorkeling = new Activity("Snorkeling", "Explore underwater life", 30.0, 15);

		// Create destinations and add activities to them
		Destination destination1 = new Destination("Mountain Resort");
		destination1.addActivity(hiking);

		Destination destination2 = new Destination("Beach Resort");
		destination2.addActivity(snorkeling);

		// Create some passengers
		Passenger passenger1 = new Passenger("Alice", 1, PassengerType.STANDARD, 100.0);
		Passenger passenger2 = new Passenger("Bob", 2, PassengerType.GOLD, 200.0);

		// Create a travel package and add passengers & destinations to it
		TravelPackage travelPackage = new TravelPackage("Adventure Package", 20);
		travelPackage.addDestination(destination1);
		travelPackage.addDestination(destination2);

		travelPackage.setPassengers( Arrays.asList(passenger1, passenger2));
		// Sign up passengers for activities
		boolean signUpSuccess1 = travelPackage.signUpForPassengerDestinationActivity(passenger1, hiking, destination1);
		assertTrue(signUpSuccess1);

		boolean signUpSuccess2 = travelPackage.signUpForPassengerDestinationActivity(passenger2, snorkeling, destination2);
		assertTrue(signUpSuccess2);
		passenger1.printDetails();
	}

	@Test
	public void testPrintAvailableActivities() {
		// Create some activities
		Activity hiking = new Activity("Hiking", "Enjoy nature on a hiking trail", 20.0, 10);
		Activity snorkeling = new Activity("Snorkeling", "Explore underwater life", 30.0, 15);
		Activity cycling = new Activity("Cycling", "Ride through scenic routes", 25.0, 5);

		// Create destinations and add activities to them
		Destination destination1 = new Destination("Mountain Resort");
		destination1.addActivity(hiking);
		destination1.addActivity(cycling);

		Destination destination2 = new Destination("Beach Resort");
		destination2.addActivity(snorkeling);

		// Create a travel package and add destinations to it
		TravelPackage travelPackage = new TravelPackage("Adventure Package", 20);
		travelPackage.addDestination(destination1);
		travelPackage.addDestination(destination2);

		Passenger passenger1 = new Passenger("Alice", 1, PassengerType.STANDARD, 100.0);
		Passenger passenger2 = new Passenger("Bob", 2, PassengerType.GOLD, 200.0);

		travelPackage.setPassengers(Arrays.asList(passenger1, passenger2));

		boolean activitySignUpSuccess1 = travelPackage.signUpForPassengerDestinationActivity(passenger1, hiking, destination1);
		boolean noActivityFoundInDestination = travelPackage.signUpForPassengerDestinationActivity(passenger1, snorkeling, destination1);
		boolean activitySignUpSuccess2 = travelPackage.signUpForPassengerDestinationActivity(passenger1, cycling, destination1);

		assertTrue(activitySignUpSuccess1);
		assertFalse(noActivityFoundInDestination);
		assertTrue(activitySignUpSuccess2);
		// Print details of available activities
		travelPackage.printAvailableActivities();
	}

	@Test
	public void testPrintItinerary() {
		// Create some activities
		Activity hiking = new Activity("Hiking", "Enjoy nature on a hiking trail", 20.0, 10);
		Activity snorkeling = new Activity("Snorkeling", "Explore underwater life", 30.0, 15);

		// Create destinations and add activities to them
		Destination destination1 = new Destination("Mountain Resort");
		destination1.addActivity(hiking);

		Destination destination2 = new Destination("Beach Resort");
		destination2.addActivity(snorkeling);

		// Create a travel package and add destinations to it
		TravelPackage travelPackage = new TravelPackage("Adventure Package", 20);
		travelPackage.addDestination(destination1);
		travelPackage.addDestination(destination2);

		// Expected itinerary string
		String expectedItinerary = """
				Travel package: Adventure Package
				Itinerary:
				Destination: Mountain Resort
				Activity: Hiking
				Cost: 20.0
				Capacity: 10
				Description: Enjoy nature on a hiking trail
				Destination: Beach Resort
				Activity: Snorkeling
				Cost: 30.0
				Capacity: 15
				Description: Explore underwater life
				""";

		// Redirect System.out to capture printed output
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// Call printItinerary method
		travelPackage.printItinerary();

		// Restore normal System.out
		System.setOut(System.out);

		// Verify printed output matches expected itinerary
		assertEquals(expectedItinerary, outContent.toString());
	}


	@Test
	public void testPrintPassengerList() {
		// Create some activities
		Activity hiking = new Activity("Hiking", "Enjoy nature on a hiking trail", 20.0, 10);
		Activity snorkeling = new Activity("Snorkeling", "Explore underwater life", 30.0, 15);

		// Create destinations and add activities to them
		Destination destination1 = new Destination("Mountain Resort");
		destination1.addActivity(hiking);

		Destination destination2 = new Destination("Beach Resort");
		destination2.addActivity(snorkeling);

		// Create a travel package and add destinations to it
		TravelPackage travelPackage = new TravelPackage("Adventure Package", 20);
		travelPackage.addDestination(destination1);
		travelPackage.addDestination(destination2);

		// Create some passengers
		Passenger passenger1 = new Passenger("Alice", 1, PassengerType.STANDARD, 100.0);
		Passenger passenger2 = new Passenger("Bob", 2, PassengerType.GOLD, 200.0);

		// Sign up passengers for activities
		travelPackage.setPassengers(Arrays.asList(passenger1, passenger2));

		travelPackage.signUpForPassengerDestinationActivity(passenger1, hiking, destination1);
		travelPackage.signUpForPassengerDestinationActivity(passenger2, snorkeling, destination2);

		// Expected passenger list string
		String expectedPassengerList = """
				Travel Package: Adventure Package
				Passenger Capacity: 20
				Number of Passengers Enrolled: 2
				Passenger List:
				Passenger Name: Alice, Passenger Number: 1
				Passenger Name: Bob, Passenger Number: 2
				""";

		// Redirect System.out to capture printed output
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// Call printPassengerList method
		travelPackage.printPassengerList();

		// Restore normal System.out
		System.setOut(System.out);

		// Verify printed output matches expected passenger list
		assertEquals(expectedPassengerList, outContent.toString());
	}


	@Test
	public void testPrintDetails() {
		// Create some activities
		Activity hiking = new Activity("Hiking", "Enjoy nature on a hiking trail", 20.0, 10);
		Activity snorkeling = new Activity("Snorkeling", "Explore underwater life", 30.0, 15);

		// Create destinations and add activities to them
		Destination destination1 = new Destination("Mountain Resort");
		destination1.addActivity(hiking);

		Destination destination2 = new Destination("Beach Resort");
		destination2.addActivity(snorkeling);

		// Create a passenger
		Passenger passenger = new Passenger("Alice", 1, PassengerType.STANDARD, 100.0);

		// Sign up passenger for activities
		passenger.signUpForActivity(hiking, destination1);
		passenger.signUpForActivity(snorkeling, destination2);

		// Expected passenger details string
		String expectedDetails = """
				Passenger name: Alice
				Passenger number: 1
				Balance: 100.0
				Activities Signed Up:\s
				Activity Name: Snorkeling
				Destination: Beach Resort
				Price Paid: 30.0
				Activity Name: Hiking
				Destination: Mountain Resort
				Price Paid: 20.0             
				""";

		// Redirect System.out to capture printed output
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// Call printDetails method
		passenger.printDetails();

		// Restore normal System.out
		System.setOut(System.out);

		// Verify printed output matches expected passenger details
		assertEquals(expectedDetails, outContent.toString());
	}
}
