public class DoubleRoomPricing implements RoomPricing {

    @Override
    public boolean supports(int roomType) {
        return roomType == LegacyRoomTypes.DOUBLE;
    }

    @Override
    public Money monthlyPrice() {
        return new Money(15000.0);
    }
}