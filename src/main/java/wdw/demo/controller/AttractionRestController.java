package wdw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdw.demo.model.Attraction;
import wdw.demo.service.AttractionService;

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
    public Attraction findAttractionById(@RequestParam Long id){

        Optional<Attraction> attractionFound = attractionService.getAttractionByID(id);
        if (attractionFound.isPresent()) return  attractionFound.get();

        return null;
    }

    @GetMapping("getAttractionByName")
    public Attraction findAttractionByName(@RequestParam String attractionName){

        Optional<Attraction> attractionFound = attractionService.getAttractionByAttractionName(attractionName);
        if (attractionFound.isPresent()) return  attractionFound.get();

        return null;
    }

    @PostMapping(path="addAttraction", consumes = "application/JSON")
    public Attraction addAttraction(@RequestBody Attraction attraction){

        Attraction attractionCreated = attractionService.createAttraction(attraction);

        return attractionCreated;
    }
    @DeleteMapping("deleteAttraction")
    public Attraction deleteAttraction(@RequestParam Long id){

        Attraction deteledAttraction = attractionService.deleleteAttraction(id);

        return deteledAttraction;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Attraction> updateAttraction  (@PathVariable Long id, @RequestBody Attraction dataAttraction) {

        Optional<Attraction> attractionToUpdate = attractionService.getAttractionByID(id);

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
