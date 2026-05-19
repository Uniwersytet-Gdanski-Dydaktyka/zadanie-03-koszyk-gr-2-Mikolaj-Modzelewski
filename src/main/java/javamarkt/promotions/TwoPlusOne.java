package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;

public class TwoPlusOne implements Promotion {
    @Override
    public Product[] apply(Product[] products) {
        if (products == null || products.length < 3) return products; 
        
        Product cheapest = null;
        for (Product p : products) {
            if (cheapest == null || cheapest.getDiscountPrice() > p.getDiscountPrice()) cheapest = p;
        }

        cheapest.setDiscountPrice(0);

        return products;
    }
}
