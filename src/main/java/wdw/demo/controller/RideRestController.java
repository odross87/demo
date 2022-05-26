package wdw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdw.demo.model.Ride;
import wdw.demo.service.RideService;

import java.util.Optional;

@RestController
@RequestMapping("apiRide")
public class RideRestController {
    @Autowired
    RideService rideService;

    @GetMapping("rides")
    public Iterable<Ride> getAllRides(){

        return rideService.ListAllRides();
    }

    @GetMapping("getRide")
    public Ride findRideById(@RequestParam Long id){

        Optional<Ride> rideFound = rideService.getRideByID(id);
        if (rideFound.isPresent()) return  rideFound.get();

        return null;
    }

    @PostMapping(path="addRide", consumes = "application/JSON")
    public Ride addRide(@RequestBody Ride ride){

        Ride rideCreated = rideService.createRide(ride);

        return rideCreated;
    }
    @DeleteMapping("deleteRide")
    public Ride deleteRide(@RequestParam Long id){

        Ride deteledRide = rideService.deleleteRide(id);

        return deteledRide;
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
