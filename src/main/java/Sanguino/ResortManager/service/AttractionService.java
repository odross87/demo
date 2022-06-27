package Sanguino.ResortManager.service;

import Sanguino.ResortManager.model.Attraction;
import Sanguino.ResortManager.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionService{

    @Autowired
    AttractionRepository attractionRepository;

    public Iterable<Attraction> ListAllAttractions() {

        Iterable<Attraction> attractions = attractionRepository.findAll();

        return attractions;
    }

    public Iterable<Attraction> ListAllAttractionsFromAPark(String parkName) {

        Iterable<Attraction> attractions = attractionRepository.findByPark(parkName);

        return attractions;
    }

    public List<Attraction> findAttractionByAttractionNameContaining(String name){

        List<Attraction> attractions = attractionRepository.findByAttractionNameContainingIgnoreCase(name);

        return attractions;
    }

    public Optional<Attraction> getAttractionById(@RequestParam String id) {
        Optional<Attraction> attraction = attractionRepository.findById(id);

        return attraction;
    }

    public Optional<Attraction> getAttractionByAttractionName(@RequestParam String attractionName){

        return attractionRepository.findAttractionByAttractionName(attractionName);
    }

    public Attraction createAttraction(Attraction attractionToCreate) {

        Attraction attractionCreated = attractionRepository.save(attractionToCreate);

        return attractionCreated;

    }

    public Attraction deleleteAttraction(String id) {
        Optional<Attraction> attractionContainer = getAttractionById(id);

            if(attractionContainer.isPresent()){
                Attraction attractionFound = attractionContainer.get();
                attractionRepository.delete(attractionFound);
                return attractionFound;
            } else {
                return null;
            }
    }

    public Attraction updateAttraction(Attraction attraction) {

        return attractionRepository.save(attraction);
    }
}
