package Sanguino.ResortManager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import Sanguino.ResortManager.model.Attraction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AttractionRepository extends MongoRepository<Attraction, String> {

    Optional<Attraction> findAttractionByAttractionName(String AttractionName);
    List<Attraction> findByAttractionNameContainingIgnoreCase(String AttractionName);

    @Query("{'park.parkName': {'$regex' : ?0}}")
    Iterable<Attraction> findByPark(String parkName);

}