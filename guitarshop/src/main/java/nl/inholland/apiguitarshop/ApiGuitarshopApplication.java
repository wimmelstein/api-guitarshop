package nl.inholland.apiguitarshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiGuitarshopApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ApiGuitarshopApplication.class.getName());

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ApiGuitarshopApplication.class, args);
		LOG.info("Application started");
	}


}
