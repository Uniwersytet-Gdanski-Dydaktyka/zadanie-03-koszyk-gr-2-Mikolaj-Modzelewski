package src.test.java.javamarkt.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.javamarkt.model.Product;
import src.main.java.javamarkt.promotions.Promotion;
import src.main.java.javamarkt.cart.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
    }

    @Test
    void shouldAddProductToCart() {
        cart.addProduct(new Product("001", "Test Product", 100));
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    void shouldIgnoreNullProductWhenAdding() {
        cart.addProduct(null);
        assertTrue(cart.getProducts().isEmpty());
    }

    @Test
    void shouldReturnDefensiveCopyOfProducts() {
        cart.addProduct(new Product("001", "P1", 100));
        List<Product> products = cart.getProducts();
        products.clear(); 
        
        assertEquals(1, cart.getProducts().size());
    }

    @Test
    void shouldCalculateTotalDiscountPrice() {
        Product p1 = new Product("001", "P1", 100);
        Product p2 = new Product("002", "P2", 200);
        p2.setDiscountPrice(150);

        cart.addProduct(p1);
        cart.addProduct(p2);

        assertEquals(250.0, cart.getTotalDiscountPrice(cart.getProducts()));
    }

    @Test
    void shouldGetTopNProducts() {
        cart.addProduct(new Product("001", "P1", 300));
        cart.addProduct(new Product("002", "P2", 100));
        cart.addProduct(new Product("003", "P3", 200));

        List<Product> top2Cheapest = cart.getTopNProducts(Comparators.BY_PRICE_ASC, 2);
        
        assertEquals(2, top2Cheapest.size());
        assertEquals("002", top2Cheapest.get(0).getCode());
        assertEquals("003", top2Cheapest.get(1).getCode());
    }

    @Test
    void shouldGetCheapestProductUsingTopN() {
        cart.addProduct(new Product("001", "P1", 300));
        cart.addProduct(new Product("002", "P2", 100));

        List<Product> cheapest = cart.getTopNProducts(Comparators.BY_PRICE_ASC, 1);
        
        assertEquals(1, cheapest.size());
        assertEquals("002", cheapest.get(0).getCode());
    }

    @Test
    void shouldThrowExceptionWhenNIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.getTopNProducts(Comparators.BY_PRICE_ASC, -1);
        });
    }

    @Test
    void shouldSortProductsInCart() {
        cart.addProduct(new Product("001", "P1", 300));
        cart.addProduct(new Product("002", "P2", 100));

        cart.sortProducts(Comparators.BY_PRICE_ASC);
        
        assertEquals("002", cart.getProducts().get(0).getCode());
    }

    @Test
    void shouldNotModifyOriginalCartWhenApplyingPromotions() {
        cart.addProduct(new Product("001", "P1", 500));
        
        Promotion dummyPromotion = products -> {
            products.get(0).setDiscountPrice(0);
            return products;
        };

        List<Product> simulatedResult = cart.applyPromotions(Collections.singletonList(dummyPromotion));
        
        assertEquals(0.0, simulatedResult.get(0).getDiscountPrice());
        assertEquals(500.0, cart.getProducts().get(0).getDiscountPrice());
    }
}