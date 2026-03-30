import java.util.List;

public class CgrRule implements EligibilityRule {

    @Override
    public void evaluate(StudentProfile s, List<String> reasons) {
        if (s.cgr < 8.0) {
            reasons.add("CGR below 8.0");
        }
    }
}