package minitienda;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private final Map<String, Integer> items = new HashMap<>();

    public void addItem(String item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public void removeItem(String item) {
        items.remove(item);
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (String item : items.keySet()) {
            String[] details = item.split("\\|");
            double price = Double.parseDouble(details[3].trim().replace("$", ""));
            total += price * items.get(item);
        }
        return total;
    }
}
