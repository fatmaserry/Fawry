package model;

import java.util.*;

public class Cart {
    private final Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product product, int quantity) throws Exception {
        if (quantity > product.getQuantity()) throw new Exception(product.getName() + " does not have enough stock.");
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }
    public Map<Product, Integer> getItems() { return items; }
    public boolean isEmpty() { return items.isEmpty(); }
}
