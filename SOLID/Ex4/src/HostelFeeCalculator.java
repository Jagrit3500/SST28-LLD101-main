import java.util.*;

public class HostelFeeCalculator {

    private final FakeBookingRepo repo;

    private final List<RoomPricing> roomPricings = List.of(
            new SingleRoomPricing(),
            new DoubleRoomPricing(),
            new TripleRoomPricing(),
            new DeluxeRoomPricing()
    );

    private final List<AddOnPricing> addOnPricings = List.of(
            new MessPricing(),
            new LaundryPricing(),
            new GymPricing()
    );

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
    }

    public void process(BookingRequest req) {

        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {

        Money base = roomPricings.stream()
                .filter(r -> r.supports(req.roomType))
                .findFirst()
                .orElseThrow()
                .monthlyPrice();

        Money addOnsTotal = new Money(0.0);

        for (AddOn a : req.addOns) {
            for (AddOnPricing p : addOnPricings) {
                if (p.supports(a)) {
                    addOnsTotal = addOnsTotal.plus(p.price());
                }
            }
        }

        return base.plus(addOnsTotal);
    }
}