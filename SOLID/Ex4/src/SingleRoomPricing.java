public class SingleRoomPricing implements RoomPricing {

    @Override
    public boolean supports(int roomType) {
        return roomType == LegacyRoomTypes.SINGLE;
    }

    @Override
    public Money monthlyPrice() {
        return new Money(14000.0);
    }
}