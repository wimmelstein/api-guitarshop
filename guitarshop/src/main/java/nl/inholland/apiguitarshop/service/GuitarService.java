package nl.inholland.apiguitarshop.service;

import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.dto.GuitarDTO;
import nl.inholland.apiguitarshop.repository.BrandRepository;
import nl.inholland.apiguitarshop.repository.GuitarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {

    private final GuitarRepository guitarRepository;

    private final BrandRepository brandRepository;

    public GuitarService(GuitarRepository guitarRepository, BrandRepository brandRepository) {
        this.guitarRepository = guitarRepository;
        this.brandRepository = brandRepository;
    }

    public List<Guitar> getAllGuitars() {
        return guitarRepository.findAll();
    }

    public Guitar createGuitar(GuitarDTO guitar) {
        return guitarRepository.save(new Guitar(brandRepository.findBrandByName(guitar.getBrand()), guitar.getModel(), guitar.getPrice()));
    }
}
