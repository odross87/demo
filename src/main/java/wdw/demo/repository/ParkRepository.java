package wdw.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import wdw.demo.model.Park;

import java.util.Optional;
public interface ParkRepository extends CrudRepository<Park, Long> {

    Optional<Park> findParkByParkName(String parkName);


}
