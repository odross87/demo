package wdw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wdw.demo.service.ParkService;
import wdw.demo.model.Park;

import java.util.Optional;

@RestController
@RequestMapping("apiPark")
public class ParkRestController {

    @Autowired
    ParkService parkservice;



    @GetMapping("parks")
    public Iterable<Park> getAllParks() {
        //
        return parkservice.listAllParks();
    }




    @GetMapping("getPark")
    public Park findParkById(@RequestParam Long id){

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
    public Park deletePark (@RequestParam Long id){

        return parkservice.deleteParkById(id);
    }



    @PutMapping("/updatePark/{id}")
    public ResponseEntity<Park> updatePark (@PathVariable Long id, @RequestBody Park dataPark) {

        Optional<Park> parkFound = parkservice.findParkById(id);

        if (parkFound.isPresent()) {

            Park parkToUpdate = parkFound.get();

            if  (dataPark.getParkName() != null) {
                parkToUpdate.setParkName(dataPark.getParkName());
            }
            if  (dataPark.getSurface() != 0) {
                parkToUpdate.setSurface(dataPark.getSurface());
            }
            if (dataPark.getOpeningYear() != 0){
                parkToUpdate.setOpeningYear(dataPark.getOpeningYear());
            }

            //Park parkUpdated = parkservice.updatePark(parkToUpdate);
            return ResponseEntity.accepted().body(null);
        } else  return ResponseEntity.accepted().body(null);

    }




}
