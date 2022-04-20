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

        List.of(
                new Brand("Fender"),
                new Brand("Gibson")
        ).forEach(brandRepository::save);



        List<Guitar> guitars = new ArrayList<>(List.of(
                new Guitar(brandRepository.findBrandByName("Fender"), "Telecaster", 1450.00 ),
                new Guitar(brandRepository.findBrandByName("Fender"), "Stratocaster", 1750.00),
                new Guitar(brandRepository.findBrandByName("Gibson"), "Les Paul", 2250.00 )
        ));

        guitars.forEach(
                guitarRepository::save
        );

       guitarRepository.findAll().forEach(
              g -> stockItemRepository.save(new StockItem(g)));

        stockItemRepository.findAll().forEach(System.out::println);
        System.out.println("--------");

        stockItemRepository.findStockItemByQuantityLessThanEqual(25).forEach(System.out::println);

    }
}
