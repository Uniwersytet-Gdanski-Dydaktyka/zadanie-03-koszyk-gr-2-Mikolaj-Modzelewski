package src.test.java.javamarkt.model;

import src.main.java.javamarkt.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProductSuccessfully() {
        Product p = new Product("001", "Mleko", 3.50);
        assertEquals("001", p.getCode());
        assertEquals("Mleko", p.getName());
        assertEquals(3.50, p.getPrice());
        assertEquals(3.50, p.getDiscountPrice());
    }

    @Test
    void shouldThrowExceptionWhenCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(null, "Test", 10);
        });
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("001", "Test", -5);
        });
    }

    @Test
    void shouldNotSetNegativeDiscountPrice() {
        Product p = new Product("001", "Test", 100);
        p.setDiscountPrice(-20);
        assertEquals(0.0, p.getDiscountPrice());
    }

    @Test
    void shouldResetDiscount() {
        Product p = new Product("001", "Test", 100);
        p.setDiscountPrice(40);
        p.resetDiscount();
        assertEquals(100.0, p.getDiscountPrice());
    }

    @Test
    void productsWithSameCodeShouldBeEqual() {
        Product p1 = new Product("XYZ", "A", 100);
        Product p2 = new Product("XYZ", "B", 200);
        
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}