package wdw.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import wdw.demo.service.ParkService;
import wdw.demo.service.RideService;
import wdw.demo.model.Park;
import wdw.demo.model.Ride;
import java.util.Scanner;

@Component
public class ApplicationCommandRunner implements CommandLineRunner {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    ParkService parkService;

    @Autowired
    RideService rideService;

    @Override
    public void run(String... args) throws Exception {

        Scanner reader = new Scanner(System.in);

        createOneRide(reader);
    }


    public void createRides(){
        logger.info("Creating rides");


        Ride ride1 = new Ride("Expedition Everest", "Roller Coaster", "Wander into a Tibetan village at the base of Mount Everest and board a train to the top of the world.");
        Ride ride2 = new Ride("Kali River Rapids", "Water Ride", "Skim across an erupting geyser, drift below a canopy of lush vegetation and be whisked along fast-moving rapids.");
        Ride ride3 = new Ride("Big Thunder Mountain", "Roller Coaster", "Race through a haunted gold mine aboard a speeding train on this thrilling coaster-style ride.");

        logger.info("Rides created");
        logger.info("Creating parks");


        Park park1 = new Park("Magic Kingdom", 49, 1975);
        Park park2 = new Park("Epcot Center",68, 1982);
        Park park3 = new Park("Hollywood Studios", 52, 1987);
        Park park4 = new Park("Animal Kingdom", 89,2001);

        logger.info("Parks created");
        logger.info("Matching parks with rides ");

        park1.addRide(ride3);
        park4.addRide(ride1);
        park4.addRide(ride2);

        parkService.createPark(park1);
        parkService.createPark(park2);
        parkService.createPark(park3);
        parkService.createPark(park4);

        rideService.createRide(ride1);
        rideService.createRide(ride2);
        rideService.createRide(ride3);

        logger.info("Finishing creating entries...");

    }

    public void createOnePark(Scanner reader){

        logger.info("Welcome to the Parks");

        System.out.println("Park's Name?");
        String name = reader.nextLine();
        System.out.println("Surface? ");
        int surface = reader.nextInt();
        System.out.println("Opening Year? ");
        int year = reader.nextInt();

        parkService.createPark(new Park(name, surface,year));

        logger.info("finishing createPark ...");
    }

    public void createOneRide(Scanner reader){
        logger.info("Welcome to the Parks");

        System.out.println("Ride's Name?");
        String name = reader.nextLine();
        System.out.println("Description? ");
        String description = reader.nextLine();
        System.out.println("Type? ");
        String type = reader.nextLine();
        System.out.println("In which park is located? (Choose the ID)");
        long idPark = reader.nextLong();

        Park park = parkService.findParkById(idPark).get();
        Ride rideCreated = new Ride(name, type, description);
        park.addRide(rideCreated);

        rideService.createRide(rideCreated);

        logger.info("finishing createPark ...");
    }

}