import java.util.List;

public interface EligibilityRule {
    void evaluate(StudentProfile s, List<String> reasons);
}