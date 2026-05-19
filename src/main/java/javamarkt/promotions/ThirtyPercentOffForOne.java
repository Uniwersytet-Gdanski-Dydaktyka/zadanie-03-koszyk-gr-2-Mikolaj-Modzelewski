package src.main.java.javamarkt.promotions;

import src.main.java.javamarkt.model.Product;
import java.util.List;

public class ThirtyPercentOffForOne implements Promotion {
    private final String productCode;

    public ThirtyPercentOffForOne(String productCode) {
        this.productCode = productCode;
    }

    @Override
    public List<Product> apply(List<Product> products) {
        if (products == null || products.isEmpty()) return products;

        for (Product p : products) {
            if (p != null && p.getCode().equals(productCode)) {
                p.setDiscountPrice(p.getDiscountPrice() * 0.70);
                break;
            }
        }
        return products;
    }
}