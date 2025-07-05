package model;

public class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected boolean expirable;
    protected boolean shippable;
    protected double weight; // in kg, only for shippable
    protected java.time.LocalDate expiryDate;

    // Basic Product
    public Product(String name, double price, int quantity) {
        this(name, price, quantity, false, null, false, 0.0);
    }

    // Expirable Product
    public Product(String name, double price, int quantity, java.time.LocalDate expiryDate) {
        this(name, price, quantity, true, expiryDate, false, 0.0);
    }

    // Shippable Product
    public Product(String name, double price, int quantity, double weight) {
        this(name, price, quantity, false, null, true, weight);
    }

    // Expirable - Shippable Product
    public Product(String name, double price, int quantity, java.time.LocalDate expiryDate, double weight) {
        this(name, price, quantity, true, expiryDate, true, weight);
    }

    // Main constructor
    private Product(String name, double price, int quantity, boolean expirable, java.time.LocalDate expiryDate, boolean shippable, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirable = expirable;
        this.expiryDate = expiryDate;
        this.shippable = shippable;
        this.weight = weight;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void reduceQuantity(int amount) { this.quantity -= amount; }
    public boolean isExpired() {
        if (!expirable) return false;
        return java.time.LocalDate.now().isAfter(getExpiryDate());
    }
    public java.time.LocalDate getExpiryDate() { return expiryDate; }
    public boolean isShippable() { return shippable; }
    public double getWeight() { return weight; }
}
