package nl.inholland.apiguitarshop.service;

import nl.inholland.apiguitarshop.model.Brand;
import nl.inholland.apiguitarshop.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public Iterable<Brand> getAllBrands() {
        return repository.findAll();
    }
}
