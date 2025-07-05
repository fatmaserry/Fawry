import model.*;
import services.CheckoutService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // TEST 1: Normal Checkout with Multiple Products
        try {
            System.out.println("Test 1: Normal Checkout with Multiple Products");
            Product cheese = new Product("Cheese", 100, 5, LocalDate.now().plusDays(5), 0.2); // expirable, shippable
            Product biscuits = new Product("Biscuits", 150, 1, LocalDate.now().plusDays(2), 0.7); // expirable, shippable
            Product tv = new Product("TV", 5000, 3, 7.0); // shippable, not expirable
            Product scratchCard = new Product("Scratch Card", 50, 10); // not expirable, not shippable

            Customer customer = new Customer("Ali", 20000);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // TEST 2: Product Expired
        try {
            System.out.println("\nTest 2: Attempt to Checkout with Expired Product");
            Product expiredCheese = new Product("Cheese", 100, 5, LocalDate.now().minusDays(1), 0.2);
            Customer customer = new Customer("Sara", 1000);
            Cart cart = new Cart();
            cart.add(expiredCheese, 1);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // TEST 3: Insufficient Balance
        try {
            System.out.println("\nTest 3: Insufficient Balance");
            Product tv = new Product("TV", 5000, 3, 7.0);
            Customer customer = new Customer("John", 100); // Not enough for a TV
            Cart cart = new Cart();
            cart.add(tv, 1);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // TEST 4: Out of Stock
        try {
            System.out.println("\nTest 4: Attempt to Add More Than Available Stock");
            Product cheese = new Product("Cheese", 100, 2, LocalDate.now().plusDays(5), 0.2);
            Customer customer = new Customer("Mona", 1000);
            Cart cart = new Cart();
            cart.add(cheese, 3); // Only 2 in stock

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // TEST 5: Cart is Empty
        try {
            System.out.println("\nTest 5: Cart is Empty");
            Customer customer = new Customer("Omar", 1000);
            Cart cart = new Cart(); // No items added

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // TEST 6: Only Non-Shippable Items
        try {
            System.out.println("\nTest 6: No Shipping Notice");
            Product scratchCard = new Product("Scratch Card", 50, 10);
            Customer customer = new Customer("Laila", 1000);
            Cart cart = new Cart();
            cart.add(scratchCard, 2);

            CheckoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
