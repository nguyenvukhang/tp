package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentID;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "A0123456B";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_HANDLE = "@amy_bee";

    private Name name;
    private StudentID studentId;
    private Phone phone;
    private Email email;
    private TelegramHandle handle;
    private Set<Tutorial> tutorials;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentID(DEFAULT_STUDENT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        handle = new TelegramHandle(DEFAULT_HANDLE);
        tutorials = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        studentId = studentToCopy.getStudentId();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        handle = studentToCopy.getHandle();
        tutorials = new HashSet<>(studentToCopy.getTutorials());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code StudentID} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentID(studentId);
        return this;
    }

    /**
     * Parses the {@code tutorials} into a {@code Set<Tutorial>} and set it to the
     * {@code Student} that we are building.
     */
    public StudentBuilder withTutorials(String... tutorials) {
        this.tutorials = SampleDataUtil.getTutorialSet(tutorials);
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Student} that we are building.
     */
    public StudentBuilder withHandle(String handle) {
        this.handle = new TelegramHandle(handle);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Student build() {
        return new Student(name, studentId, phone, email, handle, tutorials);
    }

}
