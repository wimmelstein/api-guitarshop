package nl.inholland.apiguitarshop.service;

import nl.inholland.apiguitarshop.model.Guitar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {

    private List<Guitar> guitars;

    public GuitarService(List<Guitar> guitars) {
        this.guitars = guitars;
    }

    public List<Guitar> getAllGuitars() {
        return guitars;
    }

    public Guitar addGuitar(Guitar guitar) {
            guitars.add(guitar);
            return guitar;
    }
}
