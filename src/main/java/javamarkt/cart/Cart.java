package src.main.java.javamarkt.cart;

import src.main.java.javamarkt.model.Product;
import src.main.java.javamarkt.promotions.Promotion;
import java.util.Arrays;
import java.util.Comparator;

public class Cart {
    private Product[] products = new Product[0];

    // Add product to cart
    public void addProduct(Product product) {
        if (product == null) return;
        products = Arrays.copyOf(products, products.length + 1);
        products[products.length - 1] = product;
    }

    // Get cart content
    public Product[] getProducts() {
        return Arrays.copyOf(products, products.length);
    }

    // Get total price after discount
    public double getTotalDiscountPrice(Product[] productArray) {
        double total = 0;
        for (Product p : productArray) {
            if (p != null) total += p.getDiscountPrice();
        }
        return total;
    }

    // Get n cheapes / most expensive products
    public Product[] getTopNProducts(Comparator<Product> comparator, int n) {
        if (products.length == 0 || n == 0) return new Product[0];
        if (n < 0) throw new IllegalArgumentException("n must be 0 >= 0");
        Product[] copy = Arrays.copyOf(products, products.length);
        Arrays.sort(copy, comparator);

        int toTake = Math.min(n, copy.length);
        return Arrays.copyOf(copy, toTake);
    }

    // Sort with given comparator
    public void sortProducts(Comparator<Product> comparator) {
        if (comparator != null && products.length > 0) {
            Arrays.sort(products, comparator);
        }
    }

    // Apply promotions sequentially
    public Product[] applyPromotions(Promotion[] promotions) {
        Product[] simulatedProducts = new Product[products.length];
        for (int i = 0; i < products.length; i++) {
            simulatedProducts[i] = new Product(products[i].getCode(), products[i].getName(), products[i].getPrice());
        }

        for (Promotion promotion : promotions) {
            if (promotion != null) {
                simulatedProducts = promotion.apply(simulatedProducts);
            }
        }
        return simulatedProducts;
    }
}
