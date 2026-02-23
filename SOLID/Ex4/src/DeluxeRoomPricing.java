public class DeluxeRoomPricing implements RoomPricing {

    @Override
    public boolean supports(int roomType) {
        return roomType == LegacyRoomTypes.DELUXE;
    }

    @Override
    public Money monthlyPrice() {
        return new Money(16000.0);
    }
}