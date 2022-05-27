package wdw.demo.repository;

import org.springframework.data.repository.CrudRepository;
import wdw.demo.model.Attraction;

import java.util.Optional;

public interface AttractionRepository extends CrudRepository<Attraction, Long> {

    Optional<Attraction> findAttractionByAttractionName(String AttractionName);


}