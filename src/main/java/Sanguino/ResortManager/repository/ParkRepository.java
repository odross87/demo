package Sanguino.ResortManager.repository;

import Sanguino.ResortManager.model.Park;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.*;

import java.util.Optional;
@Repository
public interface ParkRepository extends MongoRepository<Park, String> {

    Optional<Park> findParkByParkName(String parkName);


}
