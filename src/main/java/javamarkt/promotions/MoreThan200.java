package src.main.java.javamarkt.promotions;

import java.util.Arrays;

import src.main.java.javamarkt.model.Product;

public class MoreThan200 implements Promotion {
    @Override
    public Product[] apply(Product[] products) {
        if (products == null || products.length == 0) return products; 

        double total = 0;
        for (Product p : products) {
            if (p != null) total += p.getDiscountPrice();
        }

        if (total > 200) {
            Product[] newProducts = Arrays.copyOf(products, products.length + 1);
            newProducts[newProducts.length - 1] = new Product("mug-brnd", "Branded Mug", 0.0);
            return newProducts;
        }
        return products;
    }
}
