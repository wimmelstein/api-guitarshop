package nl.inholland.apiguitarshop.config;

import nl.inholland.apiguitarshop.model.Guitar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class ApplicationConfig {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public List<Guitar> guitars() {
        return new ArrayList<>(
                List.of(
                        new Guitar("Fender", "Telecaster", 1650.00),
                        new Guitar("Fender", "Stratocaster", 1750.00),
                        new Guitar("Gibson", "Les Paul", 2250.00)

                )
        );
    }
}
