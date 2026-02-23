import java.util.*;

public class OnboardingService {

    private final StudentRepository repo;
    private final StudentInputParser parser = new StudentInputParser();
    private final StudentValidator validator = new StudentValidator();
    private final StudentPrinter printer = new StudentPrinter();

    public OnboardingService(StudentRepository repo) {
        this.repo = repo;
    }

    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        StudentInput input = parser.parse(raw);

        List<String> errors = validator.validate(input);
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());

        StudentRecord rec = new StudentRecord(
                id,
                input.name,
                input.email,
                input.phone,
                input.program
        );

        repo.save(rec);

        printer.printSuccess(rec, repo.count());
    }
}