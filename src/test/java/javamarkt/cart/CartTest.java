package src.test.java.javamarkt.cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.javamarkt.cart.Cart;
import src.main.java.javamarkt.cart.Comparators;
import src.main.java.javamarkt.model.Product;
import src.main.java.javamarkt.promotions.Promotion;

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
        assertEquals(1, cart.getProducts().length);
    }

    @Test
    void shouldIgnoreNullProductWhenAdding() {
        cart.addProduct(null);
        assertEquals(0, cart.getProducts().length);
    }

    @Test
    void shouldReturnDefensiveCopyOfProducts() {
        cart.addProduct(new Product("001", "P1", 100));
        Product[] products = cart.getProducts();
        
        products[0] = null; 
        
        assertNotNull(cart.getProducts()[0]);
    }

    @Test
    void shouldCalculateTotalDiscountPrice() {
        Product p1 = new Product("001", "P1", 100);
        Product p2 = new Product("002", "P2", 200);
        p2.setDiscountPrice(150);

        cart.addProduct(p1);
        cart.addProduct(p2);

        Product[] cartContent = cart.getProducts();
        assertEquals(250.0, cart.getTotalDiscountPrice(cartContent));
    }

    @Test
    void shouldGetTopNProducts() {
        cart.addProduct(new Product("001", "P1", 300));
        cart.addProduct(new Product("002", "P2", 100));
        cart.addProduct(new Product("003", "P3", 200));

        Product[] top2Cheapest = cart.getTopNProducts(Comparators.BY_PRICE_ASC, 2);
        
        assertEquals(2, top2Cheapest.length);
        assertEquals("002", top2Cheapest[0].getCode());
        assertEquals("003", top2Cheapest[1].getCode());
    }

    @Test
    void shouldGetCheapestProductUsingTopN() {
        cart.addProduct(new Product("001", "P1", 300));
        cart.addProduct(new Product("002", "P2", 100));

        Product[] cheapestContainer = cart.getTopNProducts(Comparators.BY_PRICE_ASC, 1);
        
        assertEquals(1, cheapestContainer.length);
        assertEquals("002", cheapestContainer[0].getCode());
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
        
        assertEquals("002", cart.getProducts()[0].getCode());
    }

    @Test
    void shouldNotModifyOriginalCartWhenApplyingPromotions() {
        cart.addProduct(new Product("001", "P1", 500));
        
        Promotion dummyPromotion = products -> {
            if (products.length > 0 && products[0] != null) {
                products[0].setDiscountPrice(0);
            }
            return products;
        };

        Promotion[] promotions = new Promotion[]{dummyPromotion};
        Product[] simulatedResult = cart.applyPromotions(promotions);
        
        assertEquals(0.0, simulatedResult[0].getDiscountPrice());
        assertEquals(500.0, cart.getProducts()[0].getDiscountPrice());
    }
}