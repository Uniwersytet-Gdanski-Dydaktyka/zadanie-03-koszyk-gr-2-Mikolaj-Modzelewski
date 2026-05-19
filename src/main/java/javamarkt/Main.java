package src.main.java.javamarkt;

import src.main.java.javamarkt.cart.Cart;
import src.main.java.javamarkt.cart.Comparators;
import src.main.java.javamarkt.model.Product;
import src.main.java.javamarkt.promotions.*;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart(); // Initialise cart

        // Add products to cart
        cart.addProduct(new Product("wnr-lsc", "WinRAR license", 250.0));
        cart.addProduct(new Product("thmg-sss", "Thingmajig", 9999.0));
        cart.addProduct(new Product("f-spin", "Fidget Spinner", 25.0));
        cart.addProduct(new Product("w3-soo", "Witcher 3 - Wild Hunt : Sands of Ofir expantion", 200.0));

        // Sort cart
        System.out.println("Default cart sort");
        cart.sortProducts(Comparators.BY_PRICE_DESC_NAME_ASC);
        for (Product p : cart.getProducts()) {
            System.out.println(p);
        }

        // Apply promotions
        System.out.println("\n");
        Promotion[] promotions = new Promotion[] {
                new ThirtyPercentOffForOne("thmg-sss"),
                new TwoPlusOne(),
                new MoreThan200(),
                new MoreThan300()
        };

        Product[] afterPromotions = cart.applyPromotions(promotions);
        for (Product p : afterPromotions) {
            System.out.println(p);
        }

        // Print total
        System.out.format("\nTotal: %.2f\n", cart.getTotalDiscountPrice(afterPromotions));
    }
}
