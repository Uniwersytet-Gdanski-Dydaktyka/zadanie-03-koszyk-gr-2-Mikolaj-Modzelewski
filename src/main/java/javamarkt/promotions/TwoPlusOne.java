package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;
import java.util.List;

public class TwoPlusOne implements Promotion {
    @Override
    public List<Product> apply(List<Product> products) {
        if (products == null || products.size() < 3) return products; 
        
        Product cheapest = null;
        for (Product p : products) {
            if (p != null) {
                if (cheapest == null || cheapest.getDiscountPrice() > p.getDiscountPrice()) {
                    cheapest = p;
                }
            }
        }

        if (cheapest != null) {
            cheapest.setDiscountPrice(0);
        }

        return products;
    }
}