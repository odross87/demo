package Sanguino.ResortManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Sanguino.ResortManager.model.Attraction;
import Sanguino.ResortManager.service.AttractionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apiAttraction")
public class AttractionRestController {
    @Autowired
    AttractionService attractionService;

    @GetMapping("attractions")
    public Iterable<Attraction> getAllAttractions(){

        return attractionService.ListAllAttractions();
    }

    @GetMapping("getAttraction")
    public Attraction findAttractionById(@RequestParam String id){

        Optional<Attraction> attractionFound = attractionService.getAttractionById(id);
        if (attractionFound.isPresent()) return  attractionFound.get();

        return null;
    }

    @GetMapping("getAttractionByName")
    public Attraction findAttractionByName(@RequestParam String attractionName){

        Optional<Attraction> attractionFound = attractionService.getAttractionByAttractionName(attractionName);
        if (attractionFound.isPresent()) return  attractionFound.get();

        return null;
    }

    @GetMapping("getAttractionsByNameContaining")
    public List<Attraction> getAttractionsContainingName(@RequestParam String attractionName){

        return attractionService.findAttractionByAttractionNameContaining(attractionName);
    }

    @PostMapping(path="addAttraction", consumes = "application/JSON")
    public Attraction addAttraction(@RequestBody Attraction attraction){

        Attraction attractionCreated = attractionService.createAttraction(attraction);

        return attractionCreated;
    }
    @DeleteMapping("deleteAttraction")
    public Attraction deleteAttraction(@RequestParam String id){

        Attraction deteledAttraction = attractionService.deleleteAttraction(id);

        return deteledAttraction;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Attraction> updateAttraction  (@PathVariable String id, @RequestBody Attraction dataAttraction) {

        Optional<Attraction> attractionToUpdate = attractionService.getAttractionById(id);

        if (attractionToUpdate.isPresent()){

            Attraction attractionFound = attractionToUpdate.get();

            if(dataAttraction.getAttractionName() != null) {
                attractionFound.setAttractionName(dataAttraction.getAttractionName());
            }

            if(dataAttraction.getAttractionDescription() != null){
                attractionFound.setAttractionDescription(dataAttraction.getAttractionDescription());
            }

            Attraction attractionUpdated = attractionService.updateAttraction(attractionFound);

            return ResponseEntity.accepted().body(attractionUpdated);

        } else {
            return ResponseEntity.accepted().body(null);
        }

    }



}
