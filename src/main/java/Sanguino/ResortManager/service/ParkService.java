package Sanguino.ResortManager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import Sanguino.ResortManager.repository.ParkRepository;
import Sanguino.ResortManager.model.Park;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkService{

    @Autowired
    ParkRepository parkRepository;

    public Iterable<Park> listAllParks(){

        Iterable parks = parkRepository.findAll();

        return parks;
    }

    public Park createPark(Park park){

        Park parkCreated = parkRepository.save(park);

        return parkCreated;
    }

    public Optional<Park> findParkById(String id){

        return parkRepository.findById(id);
    }

    public Optional<Park> findParkByName(String name){

        return parkRepository.findParkByParkName(name);
    }

    public Park deleteParkById(String id){
        //Find out IF this id-book IS in our DB
        Optional<Park> parkContainer = findParkById(id);

        if (parkContainer.isPresent()){
            Park parkFound = parkContainer.get();
            parkRepository.delete(parkFound);
            return parkFound;
        } else {
            return null;
        }
    }

    public ResponseEntity<Park> updatePark(Park dataPark){

        Optional<Park> parkFound = findParkById(dataPark.get_id());

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

                Park parkUpdated = parkRepository.save(parkToUpdate);
                return ResponseEntity.accepted().body(parkUpdated);
            } else  return ResponseEntity.accepted().body(null);



    }

    public List<Park> listOfParks(){
        List<Park> parks = new ArrayList<>();

        for (Park park:listAllParks()
             ) {
            parks.add(park);
        }

        return parks;
    }






}
