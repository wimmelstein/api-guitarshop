package nl.inholland.apiguitarshop.service;

import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.repository.GuitarRepository;
import org.springframework.stereotype.Service;

@Service
public class GuitarService {

    private final GuitarRepository guitarRepository;

    public GuitarService(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }

    public Iterable<Guitar> getAllGuitars() {
        return guitarRepository.findAll();
    }
}
