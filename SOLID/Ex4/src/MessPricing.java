public class MessPricing implements AddOnPricing {

    @Override
    public boolean supports(AddOn addOn) {
        return addOn == AddOn.MESS;
    }

    @Override
    public Money price() {
        return new Money(1000.0);
    }
}