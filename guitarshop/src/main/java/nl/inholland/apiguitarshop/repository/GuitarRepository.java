package nl.inholland.apiguitarshop.repository;

import nl.inholland.apiguitarshop.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuitarRepository extends JpaRepository<Guitar, Long> {
}
