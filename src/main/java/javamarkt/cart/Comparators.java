package src.main.java.javamarkt.cart;

import src.main.java.javamarkt.model.Product;
import java.util.Comparator;

public class Comparators {
    public static final Comparator<Product> BY_PRICE_DESC_NAME_ASC = Comparator.comparingDouble(Product::getDiscountPrice) // By price
    .reversed()
    .thenComparing(Product::getName);
    public static final Comparator<Product> BY_PRICE_ASC = Comparator.comparingDouble(Product::getDiscountPrice);
    public static final Comparator<Product> BY_NAME_ASC = Comparator.comparing(Product::getName);
}
