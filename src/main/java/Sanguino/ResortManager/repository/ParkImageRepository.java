package Sanguino.ResortManager.repository;

import Sanguino.ResortManager.model.ParkImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkImageRepository extends MongoRepository<ParkImage, String> {

}
