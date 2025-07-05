package services;

import model.Shippable;
import java.util.*;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> itemCounts = new LinkedHashMap<>();
        Map<String, Double> itemWeights = new HashMap<>();

        for (Shippable item : items) {
            String key = item.getName() + "|" + item.getWeight();
            itemCounts.put(key, itemCounts.getOrDefault(key, 0) + 1);
            itemWeights.put(key, item.getWeight());
            totalWeight += item.getWeight();
        }
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String[] parts = entry.getKey().split("\\|");
            String name = parts[0];
            double weight = itemWeights.get(entry.getKey());
            int count = entry.getValue();
            System.out.printf("%dx %s %.0fg\n", count, name, weight * 1000);
        }
        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }
}
