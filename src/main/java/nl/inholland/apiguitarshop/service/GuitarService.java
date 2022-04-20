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

//    public List<Guitar> getAllGuitars(String brand, String model) {
//        Predicate<Guitar> brandPredicate = !Objects.isNull(brand) ?
//                g-> g.getBrand().equals(brand) :
//                g -> !g.getModel().equals("");
//        Predicate<Guitar> modelPredicate = !Objects.isNull(model) ?
//                g -> g.getModel().equals(model) :
//                g -> !g.getModel().isEmpty();
//
//        Optional<List<Guitar>> result = Optional.ofNullable(
//                guitarRepository.findAll()
//                .filter(brandPredicate)
//                .filter(modelPredicate).toList());
//
//        if (result.isPresent()) {
//            return result.get();
//        } else {
//            throw new IllegalArgumentException("Guitar not found");
//        }

//    }

    public Iterable<Guitar> getAllGuitars() {
        return guitarRepository.findAll();
    }
//
//    public Guitar addGuitar(Guitar guitar) {
//            guitars.add(guitar);
//            return guitar;
//    }
}
