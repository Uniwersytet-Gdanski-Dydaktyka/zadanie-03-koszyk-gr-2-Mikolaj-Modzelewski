package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;
import java.util.List;

public interface Promotion {
    List<Product> apply(List<Product> products);
}