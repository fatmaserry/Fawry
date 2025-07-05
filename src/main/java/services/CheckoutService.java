package services;

import model.Cart;
import model.Customer;
import model.Product;
import model.Shippable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) throws Exception {
        // If cart is empty, no proceed with checkout
        if (cart.isEmpty()) throw new Exception("Cart is empty");

        double subtotal = 0;
        double shipping = 0;
        // shippable items
        List<Shippable> toShip = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            // if the product is expired, no shipping for it
            if (p.isExpired()) throw new Exception(p.getName() + " is expired");

            // if the product is out of stock (0 quantity), no shipping for it
            if (qty > p.getQuantity()) throw new Exception(p.getName() + " out of stock");

            // calculate the total
            subtotal += p.getPrice() * qty;
            if (p.isShippable()) {
                for (int i = 0; i < qty; i++)
                    toShip.add((Shippable) p);
            }
        }

        // The shipping flat rate
        if (!toShip.isEmpty()) shipping = 30;

        double total = subtotal + shipping;
        // check on balance
        if (customer.getBalance() < total) throw new Exception("Insufficient balance");
        customer.deduct(total);
        // Print shipment
        if (!toShip.isEmpty()) ShippingService.ship(toShip);
        // Print receipt
        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%dx %s %.0f\n", entry.getValue(), entry.getKey().getName(), entry.getKey().getPrice() * entry.getValue());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shipping);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Customer balance after payment: %.0f\n", customer.getBalance());
    }
}
