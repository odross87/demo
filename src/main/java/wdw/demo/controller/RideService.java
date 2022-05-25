package wdw.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import wdw.demo.model.Park;
import wdw.demo.repository.RideRepository;
import wdw.demo.model.Ride;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RideService {

    @Autowired
    RideRepository rideRepository;

    public Iterable<Ride> ListAllRides() {

        Iterable<Ride> rides = rideRepository.findAll();

        return rides;
    }

    public Optional<Ride> getRideByID(@RequestParam Long id) {
        Optional<Ride> ride = rideRepository.findById(id);

        return ride;
    }

    public Ride createRide(Ride rideToCreate) {

        Ride rideCreated = rideRepository.save(rideToCreate);

        return rideCreated;

    }

    public Ride deleleteRide(Long id) {
        Optional<Ride> rideContainer = getRideByID(id);

            if(rideContainer.isPresent()){
                Ride rideFound = rideContainer.get();
                rideRepository.delete(rideFound);
                return rideFound;
            } else {
                return null;
            }
    }

    public Ride updateRide(Ride ride) {

        return rideRepository.save(ride);
    }
}
