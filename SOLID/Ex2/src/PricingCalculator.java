import java.util.*;

public class PricingCalculator {

    public double calculateSubtotal(Map<String, MenuItem> menu, List<OrderLine> lines) {
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
        }

        return subtotal;
    }

    public List<String> buildLineDescriptions(Map<String, MenuItem> menu, List<OrderLine> lines) {
        List<String> desc = new ArrayList<>();

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            desc.add(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        return desc;
    }
}