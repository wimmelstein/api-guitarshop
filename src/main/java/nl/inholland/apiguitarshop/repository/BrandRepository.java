package nl.inholland.apiguitarshop.repository;

import nl.inholland.apiguitarshop.model.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findBrandByName(String name);
}
