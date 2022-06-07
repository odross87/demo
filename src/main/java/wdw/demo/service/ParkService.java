package wdw.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import wdw.demo.repository.ParkRepository;
import wdw.demo.model.Park;

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

    public Optional<Park> findParkById(Long id){

        return parkRepository.findById(id);
    }

    public Optional<Park> findParkByName(String name){

        return parkRepository.findParkByParkName(name);
    }

    public Park deleteParkById(Long id){
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

        Optional<Park> parkFound = findParkById(dataPark.getParkId());

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
