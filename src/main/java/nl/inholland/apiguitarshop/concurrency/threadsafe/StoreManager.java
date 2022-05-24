package nl.inholland.apiguitarshop.concurrency.threadsafe;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StoreManager {

    List<Product> soldProducts = new ArrayList<>();

    public void populateSoldProducts() {
        for (int i = 0; i < 1000; i++) {
            Product product = new Product(i, "test_product_" + i);
            soldProducts.add(product);
            log.info("ADDED: " + product);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void displayProducts() {
        for (Product p : soldProducts) {
            log.info("Printing the sold {}", p);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
