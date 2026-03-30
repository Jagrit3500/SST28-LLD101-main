import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repository = new FileInvoiceRepository();
    private final PricingCalculator pricing = new PricingCalculator();
    private final TaxPolicy taxPolicy = new DefaultTaxPolicy();
    private final DiscountPolicy discountPolicy = new DefaultDiscountPolicy();
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        List<String> lineDescriptions = pricing.buildLineDescriptions(menu, lines);
        double subtotal = pricing.calculateSubtotal(menu, lines);

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);

        double discount = discountPolicy.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        String printable = formatter.format(
                invId,
                lineDescriptions,
                subtotal,
                taxPct,
                tax,
                discount,
                total
        );

        System.out.print(printable);

        repository.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + repository.countLines(invId) + ")");
    }
}