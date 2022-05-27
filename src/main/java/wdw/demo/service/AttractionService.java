package wdw.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import wdw.demo.repository.AttractionRepository;
import wdw.demo.model.Attraction;

import java.util.Optional;

@Service
public class AttractionService {

    @Autowired
    AttractionRepository attractionRepository;

    public Iterable<Attraction> ListAllAttractions() {

        Iterable<Attraction> attractions = attractionRepository.findAll();

        return attractions;
    }

    public Optional<Attraction> getAttractionByID(@RequestParam Long id) {
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

    public Attraction deleleteAttraction(Long id) {
        Optional<Attraction> attractionContainer = getAttractionByID(id);

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
