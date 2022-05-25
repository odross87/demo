package wdw.demo.repository;

import org.springframework.data.repository.CrudRepository;
import wdw.demo.model.Park;

public interface ParkRepository extends CrudRepository<Park, Long> {



}
