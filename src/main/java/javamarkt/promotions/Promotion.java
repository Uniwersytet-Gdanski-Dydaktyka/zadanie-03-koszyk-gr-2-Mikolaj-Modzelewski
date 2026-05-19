package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;

public interface Promotion {
    Product[] apply(Product[] products);
}
