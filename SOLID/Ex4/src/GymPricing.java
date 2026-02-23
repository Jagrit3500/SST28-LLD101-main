public class GymPricing implements AddOnPricing {

    @Override
    public boolean supports(AddOn addOn) {
        return addOn == AddOn.GYM;
    }

    @Override
    public Money price() {
        return new Money(300.0);
    }
}