package Sanguino.ResortManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import Sanguino.ResortManager.model.Attraction;
import Sanguino.ResortManager.model.Park;
import Sanguino.ResortManager.repository.AttractionRepository;
import Sanguino.ResortManager.repository.ParkRepository;
import Sanguino.ResortManager.service.AttractionService;
import Sanguino.ResortManager.service.ParkService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResortManagerApplicationTest {

    @Autowired
    ParkService parkService;

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    AttractionService attractionService;

    @Autowired
    AttractionRepository attractionRepository;

    @Test
    void CreateParksAndRides(){
        Park park1 = new Park("Animal Kingdom", 89, 2001);
        Park park2 = new Park("EPCOT Center", 68, 1982);
        Park park3 = new Park("Hollywood Studios", 52, 1987);
        Park park4 = new Park( "Magic Kingdom", 49, 1975);



        Attraction attraction1 = new Attraction("Expedition Everest", "Wander into a Tibetan village at the base of Mount Everest and board a train to the top of the world.", park1);
        Attraction attraction2 = new Attraction("Kali River Rapids", "Skim across an erupting geyser, drift below a canopy of lush vegetation and be whisked along fast-moving rapids.", park1);
        Attraction attraction3 = new Attraction( "Kilimanjaro Safaris", "Set off in an open-air vehicle for a guided tour of an African savanna—and spot live animals roaming free.", park1);
        Attraction attraction4 = new Attraction( "Dinosaur", "Travel back in time on a perilous prehistoric race to rescue a dinosaur—before the meteor strikes.", park1);
        Attraction attraction5 = new Attraction( "Avatar Flight of Passage", "Climb atop a winged mountain banshee for a breathtaking 3D flight over Pandora’s otherworldly landscape.", park1);
        Attraction attraction6 = new Attraction( "Big Thunder Mountain", "Race through a haunted gold mine aboard a speeding train on this thrilling coaster-style attraction.", park2);
        Attraction attraction7 = new Attraction( "It's a Small World", "Embark on a whimsical boat ride past a jubilant chorus of children from around the globe.", park2);
        Attraction attraction8 = new Attraction( "Jungle Cruise", "Chart a course for high adventure on a scenic and comedic boat tour of exotic rivers across Asia, Africa and South America.", park2);
        Attraction attraction9 = new Attraction( "Haunted Mansion", "Climb aboard a gloomy Doom Buggy for a grave journey through a labyrinth of haunted chambers.", park2);
        Attraction attraction10 = new Attraction( "Space Mountain", "Blast off on a rip-roaring rocket into the furthest reaches of outer space on this roller-coaster ride in the dark.", park2);
        Attraction attraction11 = new Attraction( "Gran Fiesta Tour Starring The Three Caballeros", "Take in the sights of Mexico and the antics of 3 feathered amigos on this gentle boat ride through the Mexico Pavilion.", park3);
        Attraction attraction12 = new Attraction( "Soarin' Around The World", "Take flight on a breezy, airborne adventure as you hang glide above the breathtaking wonders of the world.", park3);
        Attraction attraction13 = new Attraction( "Guardians of the Galaxy: Cosmic Rewind", "Take off on an intergalactic chase through space and time with the Guardians of the Galaxy.", park3);
        Attraction attraction14 = new Attraction( "Frozen Ever After", "Voyage to Arendelle aboard an ancient Nordic vessel as you take a musical tour of the wintery world of Frozen.", park3);
        Attraction attraction15 = new Attraction( "Test Track", "Design a virtual concept car and put it to the test on this thrilling, high-octane attraction.", park3);
        Attraction attraction16 = new Attraction( "Star Tours", "Make the jump to hyperspace on a thrilling 3D space flight to legendary destinations from the Star Wars saga.0", park4);
        Attraction attraction17 = new Attraction( "Toy Story Mania!", "Zip through an exhilarating 4D shootin’ game starring Toy Story characters—and blast away.", park4);
        Attraction attraction18 = new Attraction( "The Twilight Zone Tower of Terror", "Hurtle up and down aboard a haunted elevator-style lift. You’re about to enter… The Twilight Zone!", park4);
        Attraction attraction19 = new Attraction( "Rock 'n' Roller Coaster Starring Aerosmith", "Race along the darkened freeways of Los Angeles in a super-stretch limo to the rockin' tunes of Aerosmith.", park4);
        Attraction attraction20 = new Attraction( "Star Wars: Rise of the Resistance", "Join the Resistance in an unforgettable battle against the First Order on this exciting ride.", park4);



        parkService.createPark(park1);
        parkService.createPark(park2);
        parkService.createPark(park3);
        parkService.createPark(park4);

        assertEquals(parkRepository.count(), 4);

        attractionService.createAttraction(attraction1);
        attractionService.createAttraction(attraction2);
        attractionService.createAttraction(attraction3);
        attractionService.createAttraction(attraction4);
        attractionService.createAttraction(attraction5);
        attractionService.createAttraction(attraction5);
        attractionService.createAttraction(attraction6);
        attractionService.createAttraction(attraction7);
        attractionService.createAttraction(attraction8);
        attractionService.createAttraction(attraction9);
        attractionService.createAttraction(attraction10);
        attractionService.createAttraction(attraction11);
        attractionService.createAttraction(attraction12);
        attractionService.createAttraction(attraction13);
        attractionService.createAttraction(attraction14);
        attractionService.createAttraction(attraction15);
        attractionService.createAttraction(attraction16);
        attractionService.createAttraction(attraction17);
        attractionService.createAttraction(attraction18);
        attractionService.createAttraction(attraction19);
        attractionService.createAttraction(attraction20);


    }


    @Test
    void createParkAndNewAttractions(){
        Park park1 = new Park("Typhoon Lagoon", 35, 1989);

        Attraction attraction1 = new Attraction("Crush n Gusher","Shoot the chute on this gushing coaster-like raft ride—one of the park’s signature attractions!", park1);
        Attraction attraction2 = new Attraction("Miss Adventure Falls","Gather your crew and climb aboard for a thrilling, whitewater voyage aboard a 4-person raft.", park1);

        attractionService.createAttraction(attraction1);
        attractionService.createAttraction(attraction2);

        parkService.createPark(park1);


    }


}
