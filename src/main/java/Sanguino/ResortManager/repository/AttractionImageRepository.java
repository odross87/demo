package Sanguino.ResortManager.repository;

import Sanguino.ResortManager.model.AttractionImage;
import Sanguino.ResortManager.model.ParkImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttractionImageRepository extends MongoRepository<AttractionImage, String> {
    @Query("{ 'name' : ?0 }")
    Optional<AttractionImage> findByName(String name);
}
