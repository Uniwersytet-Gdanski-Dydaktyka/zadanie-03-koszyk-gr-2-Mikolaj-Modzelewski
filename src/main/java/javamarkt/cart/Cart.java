package src.main.java.javamarkt.cart;

import src.main.java.javamarkt.model.Product;
import src.main.java.javamarkt.promotions.Promotion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<>();

    // Add product to cart
    public void addProduct(Product product) {
        if (product == null) return;
        products.add(product);
    }

    // Get cart content
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    // Get total price after discount
    public double getTotalDiscountPrice(List<Product> productList) {
        double total = 0;
        for (Product p : productList) {
            if (p != null) total += p.getDiscountPrice();
        }
        return total;
    }

    // Get n cheapest / most expensive products
    public List<Product> getTopNProducts(Comparator<Product> comparator, int n) {
        if (products.isEmpty() || n == 0) return new ArrayList<>();
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        
        List<Product> copy = new ArrayList<>(products);
        copy.sort(comparator);

        int toTake = Math.min(n, copy.size());
        return copy.subList(0, toTake);
    }

    // Sort with given comparator
    public void sortProducts(Comparator<Product> comparator) {
        if (comparator != null && !products.isEmpty()) {
            products.sort(comparator);
        }
    }

    // Apply promotions sequentially
    public List<Product> applyPromotions(List<Promotion> promotions) {
        List<Product> simulatedProducts = new ArrayList<>();
        for (Product p : products) {
            simulatedProducts.add(new Product(p.getCode(), p.getName(), p.getPrice()));
        }

        for (Promotion promotion : promotions) {
            if (promotion != null) {
                simulatedProducts = promotion.apply(simulatedProducts);
            }
        }
        return simulatedProducts;
    }
}