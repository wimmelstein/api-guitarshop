package nl.inholland.apiguitarshop.config;

import nl.inholland.apiguitarshop.model.Brand;
import nl.inholland.apiguitarshop.model.Guitar;
import nl.inholland.apiguitarshop.model.Role;
import nl.inholland.apiguitarshop.model.StockItem;
import nl.inholland.apiguitarshop.model.User;
import nl.inholland.apiguitarshop.repository.BrandRepository;
import nl.inholland.apiguitarshop.repository.GuitarRepository;
import nl.inholland.apiguitarshop.repository.StockItemRepository;
import nl.inholland.apiguitarshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class MyApplicationRunner implements ApplicationRunner {

    private final GuitarRepository guitarRepository;
    private final StockItemRepository stockItemRepository;
    private final BrandRepository brandRepository;
    private final UserService userService;

    private static final String FENDER = "Fender";
    private static final String GIBSON = "Gibson";

    private final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class.getName());

    public MyApplicationRunner(GuitarRepository guitarRepository, StockItemRepository stockItemRepository, BrandRepository brandRepository, UserService userService) {
        this.guitarRepository = guitarRepository;
        this.stockItemRepository = stockItemRepository;
        this.brandRepository = brandRepository;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Brand> brands = List.of(
                new Brand(FENDER),
                new Brand(GIBSON)
        );

        brandRepository.saveAll(brands);

        List<Guitar> guitars = new ArrayList<>(List.of(
                new Guitar(brandRepository.findBrandByName(FENDER), "Telecaster", 3450.00),
                new Guitar(brandRepository.findBrandByName(FENDER), "Stratocaster", 1750.00),
                new Guitar(brandRepository.findBrandByName(GIBSON), "Les Paul", 2250.00)
        ));

        guitarRepository.saveAll(guitars);

        guitarRepository.findAll()
                .forEach(guitar -> stockItemRepository.save(new StockItem(guitar)));

        stockItemRepository.findAll()
                .forEach(System.out::println);

        stockItemRepository.findStockItemByQuantityLessThanEqual(25)
                .forEach(System.out::println);

        List<Guitar> toSort = guitarRepository.findAll();
        Collections.sort(toSort, (o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
        System.out.println(toSort);

        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setRoles(List.of(Role.ROLE_USER));
        userService.add(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setRoles(List.of(Role.ROLE_ADMIN));
        userService.add(admin);

    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
                .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
                .getHandlerMethods();
        map.forEach((key, value) -> logger.info("{} {}", key, value));
    }
}
