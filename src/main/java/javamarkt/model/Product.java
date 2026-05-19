package src.main.java.javamarkt.model;

import java.util.Objects;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private double discountPrice;

    public Product(String code, String name, double price) {
        if (code == null || name == null || price < 0) {
            throw new IllegalArgumentException("Error: Invalid product data");
        }
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDiscountPrice() { return discountPrice; }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice >= 0 ? discountPrice : 0;
    }

    public void resetDiscount() {
        this.discountPrice = this.price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f [Original price: %.2f]", name, code, discountPrice, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
