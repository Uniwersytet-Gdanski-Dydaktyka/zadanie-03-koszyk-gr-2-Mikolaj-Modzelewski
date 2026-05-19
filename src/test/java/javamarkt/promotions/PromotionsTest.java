package src.test.java.javamarkt.promotions;

import org.junit.jupiter.api.Test;
import src.main.java.javamarkt.model.Product;
import src.main.java.javamarkt.promotions.*;

import static org.junit.jupiter.api.Assertions.*;

class PromotionsTest {

    @Test
    void shouldAddMugWhenTotalOver200() {
        Promotion promo = new MoreThan200();
        Product[] items = new Product[]{
                new Product("001", "Expensive Item", 210)
        };

        Product[] result = promo.apply(items);
        
        assertEquals(2, result.length);
        assertEquals("mug-brnd", result[1].getCode());
        assertEquals(0.0, result[1].getDiscountPrice());
    }

    @Test
    void shouldNotAddMugWhenTotalIs200OrLess() {
        Promotion promo = new MoreThan200();
        Product[] items = new Product[]{
                new Product("001", "Item", 200)
        };

        Product[] result = promo.apply(items);
        assertEquals(1, result.length);
    }

    @Test
    void shouldApply5PercentDiscountWhenTotalOver300() {
        Promotion promo = new MoreThan300();
        Product[] items = new Product[]{
                new Product("001", "P1", 400)
        };

        Product[] result = promo.apply(items);
        assertEquals(380.0, result[0].getDiscountPrice(), 0.01);
    }

    @Test
    void shouldNotApply5PercentDiscountWhenTotalIs300OrLess() {
        Promotion promo = new MoreThan300();
        Product[] items = new Product[]{
                new Product("001", "P1", 300)
        };

        Product[] result = promo.apply(items);
        assertEquals(300.0, result[0].getDiscountPrice());
    }

    @Test
    void shouldGiveCheapestProductForFreeWhen3OrMoreItems() {
        Promotion promo = new TwoPlusOne();
        Product[] items = new Product[]{
                new Product("001", "P1", 100),
                new Product("002", "P2", 50),
                new Product("003", "P3", 200)
        };

        Product[] result = promo.apply(items);
        assertEquals(100.0, result[0].getDiscountPrice());
        assertEquals(0.0, result[1].getDiscountPrice());
        assertEquals(200.0, result[2].getDiscountPrice());
    }

    @Test
    void shouldNotApplyTwoPlusOneWhenLessThan3Items() {
        Promotion promo = new TwoPlusOne();
        Product[] items = new Product[]{
                new Product("001", "P1", 100),
                new Product("002", "P2", 50)
        };

        Product[] result = promo.apply(items);
        assertEquals(100.0, result[0].getDiscountPrice());
        assertEquals(50.0, result[1].getDiscountPrice());
    }

    @Test
    void shouldApply30PercentDiscountToSpecificProduct() {
        Promotion promo = new ThirtyPercentOffForOne("002");
        Product[] items = new Product[]{
                new Product("001", "P1", 100),
                new Product("002", "P2", 100)
        };

        Product[] result = promo.apply(items);
        assertEquals(100.0, result[0].getDiscountPrice());
        assertEquals(70.0, result[1].getDiscountPrice(), 0.01);
    }
}