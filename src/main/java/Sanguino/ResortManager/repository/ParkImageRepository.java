package Sanguino.ResortManager.repository;

import Sanguino.ResortManager.model.ParkImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkImageRepository extends MongoRepository<ParkImage, String> {
    @Query("{ 'name' : ?0 }")
    Optional<ParkImage> findByName(String name);
}
