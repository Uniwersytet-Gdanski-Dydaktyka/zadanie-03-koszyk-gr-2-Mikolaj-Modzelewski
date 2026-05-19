package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;
import java.util.List;

public class MoreThan300 implements Promotion {
    @Override
    public List<Product> apply(List<Product> products) {
        if (products == null || products.isEmpty()) return products; 

        double total = 0;
        for (Product p : products) {
            if (p != null) total += p.getDiscountPrice();
        }

        if (total > 300.0) {
            for (Product p : products) {
                if (p != null) p.setDiscountPrice(p.getDiscountPrice() * 0.95);
            }
        }
        return products;
    }
}