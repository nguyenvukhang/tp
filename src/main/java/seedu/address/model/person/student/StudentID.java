package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StudentID {

    public final String id;

    public static final String VALIDATION_REGEX = "A\\d{7}[A-Z]";
    public static final String MESSAGE_CONSTRAINTS = "Student IDs should be in the form AXXXXXXX[A-Z], "
            + "and it should not be blank";

    /**
     * Constructs a {@code StudentID}.
     *
     * @param studentId
     *            A valid student ID.
     */
    public StudentID(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidID(studentId), MESSAGE_CONSTRAINTS);
        id = studentId.trim();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidID(String test) {
        return test.matches(VALIDATION_REGEX);
    }
}
