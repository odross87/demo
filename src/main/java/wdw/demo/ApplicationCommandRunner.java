package wdw.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import wdw.demo.controller.ParkService;
import wdw.demo.controller.RideService;
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

        createRides();

    }

    public void createRides(){
        logger.info("Welcome to the Create Rides");
        // String title, String author, int pages, int year, String iSBN

        parkService.findParkById(38L).get();
        Ride ride1 = new Ride("Expedition Everest", "Roller Coaster", "Wander into a Tibetan village at the base of Mount Everest and board a train to the top of the world.", parkService.findParkById(38L).get());
        Ride ride2 = new Ride("Kali River Rapids", "Water Ride", "Skim across an erupting geyser, drift below a canopy of lush vegetation and be whisked along fast-moving rapids.", parkService.findParkById(38L).get());
        Ride ride3 = new Ride("Big Thunder Mountain", "Roller Coaster", "Race through a haunted gold mine aboard a speeding train on this thrilling coaster-style ride.",  parkService.findParkById(35L).get());

        rideService.createRide(ride1);
        rideService.createRide(ride2);
        rideService.createRide(ride3);

        logger.info("Finishing creating rides.");


    }
    public void createBooks(){
        logger.info("Welcome to the createBooks");
        // String title, String author, int pages, int year, String iSBN
        Park park1 = new Park("Magic Kingdom", 49, 1975);
        Park park2 = new Park("Epcot Center",68, 1982);
        Park park3 = new Park("Hollywood Studios", 52, 1987);
        Park park4 = new Park("Animal Kingdom", 89,2001);
        //to repo-DB via bookService
        parkService.createPark(park1);
        parkService.createPark(park2);
        parkService.createPark(park3);
        parkService.createPark(park4);

        logger.info("Finishing creating parks...");


    }

    public void createOneBook(Scanner reader){

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

}