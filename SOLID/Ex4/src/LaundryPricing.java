public class LaundryPricing implements AddOnPricing {

    @Override
    public boolean supports(AddOn addOn) {
        return addOn == AddOn.LAUNDRY;
    }

    @Override
    public Money price() {
        return new Money(500.0);
    }
}