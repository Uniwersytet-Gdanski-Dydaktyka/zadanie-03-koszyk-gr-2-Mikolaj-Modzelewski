package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;

public class MoreThan300 implements Promotion {
    @Override
    public Product[] apply(Product[] products) {
        if (products == null || products.length == 0) return products; 

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
