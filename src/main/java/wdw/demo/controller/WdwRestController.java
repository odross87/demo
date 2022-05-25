package wdw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdw.demo.controller.ParkService;
import wdw.demo.model.Park;
import wdw.demo.model.Ride;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class WdwRestController {

    @Autowired
    ParkService parkservice;

    @Autowired
    RideService rideService;

    @GetMapping("parks")
    public Iterable<Park> getAllParks() {
        //
        return parkservice.listAllParks();
    }

    @GetMapping("rides")
    public Iterable<Ride> getAllRides(){

        return rideService.ListAllRides();
    }


    @GetMapping("getPark")
    public Park findParkById(@RequestParam Long id){

        Optional<Park> parkFound = parkservice.findParkById(id);
        if (parkFound.isPresent()) return  parkFound.get();

        return null;
    }

    @GetMapping("getRide")
    public Ride findRideById(@RequestParam Long id){

        Optional<Ride> rideFound = rideService.getRideByID(id);
        if (rideFound.isPresent()) return  rideFound.get();

        return null;
    }

    @PostMapping(path="addPark", consumes = "application/JSON")
    public Park addPark(@RequestBody Park park){


        Park parkCreated = parkservice.createPark(park);

        return parkCreated ;
    }

    @PostMapping(path="addRide", consumes = "application/JSON")
    public Ride addRide(@RequestBody Ride ride){

        Ride rideCreated = rideService.createRide(ride);

        return rideCreated;
    }

    @DeleteMapping("deletePark")
    public Park deletePark (@RequestParam Long id){

        Park deletedPark = parkservice.deleteParkById(id);

        return deletedPark;
    }

    @DeleteMapping("deleteRide")
    public Ride deleteRide(@RequestParam Long id){

        Ride deteledRide = rideService.deleleteRide(id);

        return deteledRide;
    }

    @PutMapping("/updatePark/{id}")
    public ResponseEntity<Park> updatePark (@PathVariable Long id, @RequestBody Park dataPark) {

        Optional<Park> parkFound = parkservice.findParkById(id);

        if (parkFound.isPresent()) {

            Park parkToUpdate = parkFound.get();

            if  (dataPark.getPark_name() != null) {
                parkToUpdate.setPark_name(dataPark.getPark_name());
            }
            if  (dataPark.getSurface() != 0) {
                parkToUpdate.setSurface(dataPark.getSurface());
            }
            if (dataPark.getOpeningYear() != 0){
                parkToUpdate.setOpeningYear(dataPark.getOpeningYear());
            }

            Park parkUpdated = parkservice.updatePark(parkToUpdate);
            return ResponseEntity.accepted().body(parkUpdated);
        } else  return ResponseEntity.accepted().body(null);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Ride> updateRide  (@PathVariable Long id, @RequestBody Ride dataRide) {

        Optional<Ride> rideToUpdate = rideService.getRideByID(id);

        if (rideToUpdate.isPresent()){

            Ride rideFound = rideToUpdate.get();

            if(dataRide.getRide_name() != null) {
                rideFound.setRide_name(dataRide.getRide_name());
            }
            if(dataRide.getRide_type() != null){
                rideFound.setRide_type(dataRide.getRide_type());
            }
            if(dataRide.getRide_description() != null){
                rideFound.setRide_description(dataRide.getRide_description());
            }

            Ride rideUpdated = rideService.updateRide(rideFound);

            return ResponseEntity.accepted().body(rideUpdated);

        } else {
            return ResponseEntity.accepted().body(null);
        }

    }



}
