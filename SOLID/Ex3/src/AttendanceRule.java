import java.util.List;

public class AttendanceRule implements EligibilityRule {

    @Override
    public void evaluate(StudentProfile s, List<String> reasons) {
        if (s.attendancePct < 75) {
            reasons.add("attendance below 75");
        }
    }
}