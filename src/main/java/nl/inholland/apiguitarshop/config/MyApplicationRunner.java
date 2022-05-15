package nl.inholland.apiguitarshop.config;

import nl.inholland.apiguitarshop.model.Brand;
import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.StockItem;
import nl.inholland.apiguitarshop.repository.BrandRepository;
import nl.inholland.apiguitarshop.repository.GuitarRepository;
import nl.inholland.apiguitarshop.repository.StockItemRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class MyApplicationRunner implements ApplicationRunner {

    private final GuitarRepository guitarRepository;
    private final StockItemRepository stockItemRepository;
    private final BrandRepository brandRepository;

    public MyApplicationRunner(GuitarRepository guitarRepository, StockItemRepository stockItemRepository, BrandRepository brandRepository) {
        this.guitarRepository = guitarRepository;
        this.stockItemRepository = stockItemRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Brand> brands = List.of(
                new Brand("Fender"),
                new Brand("Gibson")
        );

        brandRepository.saveAll(brands);

        List<Guitar> guitars = new ArrayList<>(List.of(
                new Guitar(brandRepository.findBrandByName("Fender"), "Telecaster", 3450.00),
                new Guitar(brandRepository.findBrandByName("Fender"), "Stratocaster", 1750.00),
                new Guitar(brandRepository.findBrandByName("Gibson"), "Les Paul", 2250.00)
        ));

        guitarRepository.saveAll(guitars);

        guitarRepository.findAll()
                .forEach(guitar -> stockItemRepository.save(new StockItem(guitar)));

        stockItemRepository.findAll()
                .forEach(System.out::println);

        stockItemRepository.findStockItemByQuantityLessThanEqual(25)
                .forEach(System.out::println);

        List<Guitar> toSort = guitarRepository.findAll();
        Collections.sort(toSort, ( o1,  o2) -> (int) (o1.getPrice() - o2.getPrice()));
        System.out.println(toSort);
    }
}
