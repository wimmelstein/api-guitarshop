package nl.inholland.apiguitarshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ApiGuitarshopApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ApiGuitarshopApplication.class, args);
		Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
		System.out.println(ctx.getApplicationName());
	}
}
