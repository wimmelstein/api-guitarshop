package nl.inholland.apiguitarshop.repository;

import nl.inholland.apiguitarshop.model.Guitar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuitarRepository extends CrudRepository<Guitar, Long> {

}
