package nl.inholland.apiguitarshop.service;

import nl.inholland.apiguitarshop.model.Guitar;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class GuitarService {

    private final List<Guitar> guitars;

    public GuitarService(List<Guitar> guitars) {
        this.guitars = guitars;
    }

    public List<Guitar> getAllGuitars(String brand, String model) {
        Predicate<Guitar> brandPredicate = !Objects.isNull(brand) ?
                g-> g.getBrand().equals(brand) :
                g -> !g.getModel().equals("");
        Predicate<Guitar> modelPredicate = !Objects.isNull(model) ?
                g -> g.getModel().equals(model) :
                g -> !g.getModel().isEmpty();

        Optional<List<Guitar>> result = Optional.ofNullable(
                guitars.stream()
                .filter(brandPredicate)
                .filter(modelPredicate).toList());

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalArgumentException("Guitar not found");
        }

    }

    public Guitar addGuitar(Guitar guitar) {
            guitars.add(guitar);
            return guitar;
    }
}
