package wdw.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wdw.demo.repository.ParkRepository;
import wdw.demo.model.Park;

import java.util.Optional;

@Service
public class ParkService {

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

    public Park updatePark(Park parkToUpdate){

        return parkRepository.save(parkToUpdate);
    }






}
