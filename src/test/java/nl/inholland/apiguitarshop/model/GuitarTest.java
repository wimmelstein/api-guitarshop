package nl.inholland.apiguitarshop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GuitarTest {

    private Guitar guitar;

    @BeforeEach
    void setup() {
        guitar = new Guitar();
    }

    @Test
    void newGuitarShouldNotBeNull() {
        Assertions.assertNotNull(guitar);
    }

    @Test
    void setPriceToPositiveNumberShouldSetThatPrice() {
        int newPrice = new Random().nextInt(100000);
        guitar.setPrice(newPrice);
        assertEquals(newPrice, guitar.getPrice());
    }

    @Test
    void setPriceToNegativeShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> guitar.setPrice(-1));
    }

    @Test
    void newGuitarShouldReturnNumberOfStringsAsSix() {
        assertEquals(6, guitar.getNumberOfStrings());
    }
}