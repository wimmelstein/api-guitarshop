package nl.inholland.apiguitarshop.concurrency.threadsafe;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
}
