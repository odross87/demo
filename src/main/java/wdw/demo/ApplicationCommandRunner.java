package wdw.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import wdw.demo.service.ParkService;
import wdw.demo.service.AttractionService;
import wdw.demo.model.Park;
import wdw.demo.model.Attraction;
import java.util.Scanner;

@Component
public class ApplicationCommandRunner implements CommandLineRunner {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    ParkService parkService;

    @Autowired
    AttractionService attractionService;

    @Override
    public void run(String... args) throws Exception {

        Scanner reader = new Scanner(System.in);

    }


    public void createAttractions(){
        logger.info("Creating attractions");


        Attraction attraction1 = new Attraction(5L, "Expedition Everest", "Wander into a Tibetan village at the base of Mount Everest and board a train to the top of the world.");
        Attraction attraction2 = new Attraction(5L, "Kali River Rapids", "Skim across an erupting geyser, drift below a canopy of lush vegetation and be whisked along fast-moving rapids.");
        Attraction attraction3 = new Attraction(5L, "Big Thunder Mountain", "Race through a haunted gold mine aboard a speeding train on this thrilling coaster-style attraction.");

        logger.info("Attractions created");
        logger.info("Creating parks");


        Park park1 = new Park("Magic Kingdom", 49, 1975);
        Park park2 = new Park("Epcot Center",68, 1982);
        Park park3 = new Park("Hollywood Studios", 52, 1987);
        Park park4 = new Park("Animal Kingdom", 89,2001);

        logger.info("Parks created");
        logger.info("Matching parks with attractions ");

        park1.addAttraction(attraction3);
        park4.addAttraction(attraction1);
        park4.addAttraction(attraction2);

        parkService.createPark(park1);
        parkService.createPark(park2);
        parkService.createPark(park3);
        parkService.createPark(park4);

        attractionService.createAttraction(attraction1);
        attractionService.createAttraction(attraction2);
        attractionService.createAttraction(attraction3);

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

    public void createOneAttraction(Scanner reader){
        logger.info("Welcome to the Parks");

        System.out.println("Attraction's Name?");
        String name = reader.nextLine();
        System.out.println("Description? ");
        String description = reader.nextLine();
        System.out.println("Type? ");
        String type = reader.nextLine();
        System.out.println("In which park is located? (Choose the ID)");
        long idPark = reader.nextLong();

        Park park = parkService.findParkById(idPark).get();
        Attraction attractionCreated = new Attraction(5L, name, description);
        park.addAttraction(attractionCreated);

        attractionService.createAttraction(attractionCreated);

        logger.info("finishing createPark ...");
    }

}