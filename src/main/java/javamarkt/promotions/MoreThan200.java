package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;
import java.util.List;

public class MoreThan200 implements Promotion {
    @Override
    public List<Product> apply(List<Product> products) {
        if (products == null || products.isEmpty()) return products; 

        double total = 0;
        for (Product p : products) {
            if (p != null) total += p.getDiscountPrice();
        }

        if (total > 200) {
            products.add(new Product("mug-brnd", "Branded Mug", 0.0));
        }
        return products;
    }
}