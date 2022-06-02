package wdw.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wdw.demo.model.Attraction;
import wdw.demo.model.Park;
import wdw.demo.repository.AttractionRepository;
import wdw.demo.repository.ParkRepository;
import wdw.demo.service.AttractionService;
import wdw.demo.service.ParkService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WdwApplicationTest {

    @Autowired
    ParkService parkService;

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    AttractionService attractionService;

    @Autowired
    AttractionRepository attractionRepository;

    @Test
    void CreateParks(){
        Park park1 = new Park("Magic Kingdom", 49, 1975);
        Park park2 = new Park("EPCOT Center", 68, 1982);
        Park park3 = new Park("Hollywood Studios", 52, 1987);
        Park park4 = new Park("Animal Kingdom", 89, 2001);

        parkService.createPark(park1);
        parkService.createPark(park2);
        parkService.createPark(park3);
        parkService.createPark(park4);

        assertEquals(parkRepository.count(), 4);

    }

    @Test
    void CreateAttractions(){
        Attraction attraction1 = new Attraction("Expedition Everest", "Wander into a Tibetan village at the base of Mount Everest and board a train to the top of the world.");
        Attraction attraction2 = new Attraction("Kali River Rapids", "Skim across an erupting geyser, drift below a canopy of lush vegetation and be whisked along fast-moving rapids.");
        Attraction attraction3 = new Attraction("Big Thunder Mountain", "Race through a haunted gold mine aboard a speeding train on this thrilling coaster-style attraction.");

        attractionService.createAttraction(attraction1);
        attractionService.createAttraction(attraction2);
        attractionService.createAttraction(attraction3);

        assertEquals(attractionRepository.count(),3);

    }

    @Test
    void assignParkToNewAttractions(){
        //find by id just one park
        Optional<Park> parkFound = parkRepository.findById(205L);
        //if park exists, then..
        if(parkFound.isPresent()) {
            //create attractions
            Attraction attraction1 = new Attraction("The Twilight Zone Tower of Terror", "Hurtle up and down aboard a haunted elevator-style lift. You’re about to enter… The Twilight Zone!");
            Attraction attraction2 = new Attraction("Millennium Falcon: Smugglers Run", "Fly the Millennium Falcon on a thrilling interactive smuggling mission.");
            //assign park to attractions
            attraction1.setPark(parkFound.get());
            attraction2.setPark(parkFound.get());
            //save attractions with park
            attractionService.createAttraction(attraction1);
            attractionService.createAttraction(attraction2);
        }

    }

    @Test
    void assignNewAttractionsToPark(){
        //find by id just one park
        Optional<Park> parkFound = parkRepository.findById(205L);
        //if park exists, then..
        if(parkFound.isPresent()) {
            //create attractions
            Attraction attraction1 = new Attraction("The Twilight Zone Tower of Terror", "Hurtle up and down aboard a haunted elevator-style lift. You’re about to enter… The Twilight Zone!");
            Attraction attraction2 = new Attraction("Millennium Falcon: Smugglers Run", "Fly the Millennium Falcon on a thrilling interactive smuggling mission.");
            //save attractions without park
            attractionService.createAttraction(attraction1);
            attractionService.createAttraction(attraction2);

            //assign park to attractions
            Park park = parkFound.get();
            park.addAttraction(attraction1);
            park.addAttraction(attraction2);

            parkService.createPark(park);
        } else {
            System.out.println("no es troba");
        }
    }

    @Test
    void createParkAndNewAttractions(){
        //create attractions
        Attraction attraction1 = new Attraction("Crush n Gusher","Shoot the chute on this gushing coaster-like raft ride—one of the park’s signature attractions!");
        Attraction attraction2 = new Attraction("Miss Adventure Falls","Gather your crew and climb aboard for a thrilling, whitewater voyage aboard a 4-person raft.");
        //save attractions without park
        attractionService.createAttraction(attraction1);
        attractionService.createAttraction(attraction2);

        Park park1 = new Park("Typhoon Lagoon", 35, 1989);

        parkService.createPark(park1);

        park1.addAttraction(attraction1);
        park1.addAttraction(attraction2);

        parkService.createPark(park1);
    }






}
