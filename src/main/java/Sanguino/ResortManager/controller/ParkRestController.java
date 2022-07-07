package Sanguino.ResortManager.controller;

import Sanguino.ResortManager.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Sanguino.ResortManager.model.Park;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class ParkRestController {

    @Autowired
    ParkService parkservice;



    @GetMapping("parks")
    public Iterable<Park> getAllParks() {
        //
        return parkservice.listAllParks();
    }




    @GetMapping("getPark")
    public Park findParkById(@RequestParam String id){

        Optional<Park> parkFound = parkservice.findParkById(id);
        if (parkFound.isPresent()) return  parkFound.get();

        return null;
    }

    @GetMapping("getParkByName")
    public Park findParkbyName(@RequestParam String name){

        Optional<Park> parkFound = parkservice.findParkByName(name);
        if (parkFound.isPresent()) return parkFound.get();

        return null;
    }



    @PostMapping(path="addPark", consumes = "application/JSON")
    public Park addPark(@RequestBody Park park){

        return parkservice.createPark(park);
    }


    @DeleteMapping("deletePark")
    public Park deletePark (@RequestParam String id){

        return parkservice.deleteParkById(id);
    }



    @PutMapping("/updatePark/{id}")
    public ResponseEntity<Park> updatePark (@PathVariable String id, @RequestBody Park dataPark) {

        Optional<Park> parkFound = parkservice.findParkById(id);

        if (parkFound.isPresent()) {

            Park parkToUpdate = parkFound.get();

            if  (dataPark.getParkName() != null) {
                parkToUpdate.setParkName(dataPark.getParkName());
            }
            if  (dataPark.getParkDescription() != null) {
                parkToUpdate.setParkDescription(dataPark.getParkDescription());
            }
            if  (dataPark.getSurface() != 0) {
                parkToUpdate.setSurface(dataPark.getSurface());
            }
            if (dataPark.getOpeningYear() != 0){
                parkToUpdate.setOpeningYear(dataPark.getOpeningYear());
            }

            ResponseEntity<Park> parkUpdated = parkservice.updatePark(parkToUpdate);
            return parkUpdated;
        } else  return ResponseEntity.accepted().body(null);

    }




}
