package wdw.demo.repository;

import org.springframework.data.repository.CrudRepository;
import wdw.demo.model.Ride;

public interface RideRepository  extends CrudRepository<Ride, Long> {


}